package pl.sda.ticketing_software_sda_gp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.ticketing_software_sda_gp.exception.ElementNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.TicketMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.*;

@Service
@Slf4j
public class TicketService {
    private final MessageService messageService;
    private final TicketRepository ticketRepository;
    private final StatusRepository statusRepository;
    private final QueueRepository queueRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    public TicketService(MessageService messageService, TicketRepository ticketRepository, StatusRepository statusRepository,
                         QueueRepository queueRepository, UserRepository userRepository,
                         ConversationRepository conversationRepository) {
        this.messageService = messageService;
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    @Cacheable(cacheNames = "tickets")
    public Set<Ticket> getAllOrFilteredTickets(Long user, Long queue, Long status) {
        log.info("getAllTickets");
        if (user == null && queue == null && status == null) return ticketRepository.findAllTickets();
        else {
            Set<Ticket> tickets = new HashSet<>();
            if (user != null) {
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByUserId(user), tickets);
            }
            if (queue != null) {
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByQueueId(queue), tickets);
            }
            if (status != null) {
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByStatusId(status), tickets);
            }
            return tickets;
        }
    }

    @Transactional
    @Caching(evict = @CacheEvict(cacheNames = "tickets", allEntries = true))
    public Ticket addNewTicket(NewTicketDTO DTO) {
        log.info("create");
        Conversation newConversation = conversationRepository.save(new Conversation());
        Ticket ticket = ticketRepository.save(map(getNewStatus(statusRepository), getGeneralRecipient(userRepository), DTO, newConversation));
        messageService.addFirstMessageForNewTicket(ticket, DTO);
        return ticket;
    }

    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = @CacheEvict(cacheNames = "tickets", allEntries = true))
    public void updateTicketStatus(Long id, Status status) {
        log.info("update");
        Status updatedStatus = statusRepository.findById(status.getStatusId()).orElseThrow(() -> new ElementNotFoundException("Element not found"));
        Long statusId=status.getStatusId();
        ticketRepository.updateTicketStatus(id,statusId);
    }

    @Caching(evict = @CacheEvict(cacheNames = "tickets", allEntries = true))
    public void updateTicketQueue(Long id, Queue queue) {
        log.info("update");
        queueRepository.findById(queue.getQueueId()).orElseThrow(() -> new ElementNotFoundException("Element not found"));
        Long queueId=queue.getQueueId();
        ticketRepository.updateTicketQueue(id, queueId);
    }

    @Caching(evict = @CacheEvict(cacheNames = "tickets", allEntries = true))
    public void updateTicketUser(Long id, User user) throws ElementNotFoundException {
        log.info("update");
        userRepository.findById(user.getUserId()).orElseThrow(() -> new ElementNotFoundException("User not exist!"));
        ticketRepository.updateTicketAssignee(id, user.getUserId());
    }

    @Caching(evict = @CacheEvict(cacheNames = "tickets", allEntries = true))
    public Queue createNewQueue(Queue DTO) {
        return queueRepository.save(DTO);
    }

    @Cacheable(cacheNames = "tickets")
    public Ticket findById(Long id) throws ElementNotFoundException {
        log.info("ticketById");
        if(ticketRepository.findByTicketId(id)!=null)
            return ticketRepository.findByTicketId(id);
        else throw  new ElementNotFoundException("Element not found");
    }

    @Transactional
    @Caching(evict = @CacheEvict(cacheNames = "tickets", allEntries = true))
    public void deleteTicket(Long id) {
        log.info("deleteTicket");
        Ticket t=ticketRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Element not found"));
        ticketRepository.delete(t);
    }
}
