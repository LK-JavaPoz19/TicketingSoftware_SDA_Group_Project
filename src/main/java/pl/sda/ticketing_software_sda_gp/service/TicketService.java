package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.ticketing_software_sda_gp.exception.ResourceNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.QueueRepository;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;
import pl.sda.ticketing_software_sda_gp.repository.TicketRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.TicketMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.addElementsOrFindIntersection;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.findElementOrThrowException;

@Service
public class TicketService {
    ConversationService conversationService;
    private final TicketRepository ticketRepository;
    private final StatusRepository statusRepository;
    private final QueueRepository queueRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, StatusRepository statusRepository,
                         QueueRepository queueRepository, UserRepository userRepository,
                         ConversationService conversationService) {
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
        this.conversationService = conversationService;
    }

    public Set<Ticket> getAllOrFilteredTickets(Long user, Long queue, Long status) {

        if (user == null && queue == null && status == null) return ticketRepository.findAllTickets();
        else {
            Set<Ticket> tickets = new HashSet<>();
            if (user != null) {
                findElementOrThrowException(userRepository, user, "User with a provided ID does not exist.");
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByUserId(user), tickets);
            }
            if (queue != null) {
                findElementOrThrowException(queueRepository, queue, "Queue with a provided ID does not exist.");
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByQueueId(queue), tickets);
            }
            if (status != null) {
                findElementOrThrowException(statusRepository, status, "Status with a provided ID does not exist.");
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByStatusId(status), tickets);
            }
            return tickets;
        }
    }

    @Transactional
    public Ticket createNewTicket(NewTicketDTO DTO) {
        Ticket ticket = ticketRepository.save(map(
                findElementOrThrowException(queueRepository, DTO.getQueue(), "Queue with a provided ID does not exist."),
                findElementOrThrowException(statusRepository, 1L, "NEW ticket status not found."),
                findElementOrThrowException(userRepository, 1L, "General recipient not found.")));
        conversationService.addConversationAndFirstMessageForNewTicket(ticket, DTO);
        return ticket;
    }

    public void setTicketStatus(Long id, Long status) {
        ticketRepository.updateTicketStatus(id, statusRepository.findById(status)
                .orElseThrow(() -> new ResourceNotFoundException("Status with a provided ID does not exist.")));
    }

    public void setTicketQueue(Long id, Long queue) {
        ticketRepository.updateTicketQueue(id, queueRepository.findById(queue)
                .orElseThrow(() -> new ResourceNotFoundException("Queue with a provided ID does not exist.")));
    }

    public void setTicketAssignee(Long id, Long user) {
        ticketRepository.updateTicketAssignee(id, userRepository.findById(user)
                .orElseThrow(() -> new ResourceNotFoundException("User with a provided ID does not exist.")));
    }

    public Queue createNewQueue(Queue DTO) {
        return queueRepository.save(DTO);
    }
}
