package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.QueueRepository;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;
import pl.sda.ticketing_software_sda_gp.repository.TicketRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.TicketMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.*;

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
    public Ticket createNewTicket(NewTicketDTO DTO) {
        Ticket ticket = ticketRepository.save(map(getNewStatus(statusRepository), getGeneralRecipient(userRepository), DTO));
        conversationService.addConversationAndFirstMessageForNewTicket(ticket, DTO);
        return ticket;
    }

    public void setTicketStatus(Long id, Status status) {
        ticketRepository.updateTicketStatus(id, status);
    }

    public void setTicketQueue(Long id, Queue queue) {
        ticketRepository.updateTicketQueue(id, queue);
    }

    public void setTicketAssignee(Long id, User user) {
        ticketRepository.updateTicketAssignee(id, user);
    }

    public Queue createNewQueue(Queue DTO) {
        return queueRepository.save(DTO);
    }
}
