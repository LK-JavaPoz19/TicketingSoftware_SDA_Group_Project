package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.QueueNotFoundException;
import pl.sda.ticketing_software_sda_gp.exception.StatusNotFoundException;
import pl.sda.ticketing_software_sda_gp.exception.UserNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.QueueRepository;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;
import pl.sda.ticketing_software_sda_gp.repository.TicketRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.TicketMapper.mapNewTicket;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.addElementsOrFindIntersection;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.findElementOrThrowException;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final StatusRepository statusRepository;
    private final QueueRepository queueRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, StatusRepository statusRepository,
                         QueueRepository queueRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
    }

    public Set<Ticket> getAllOrFilteredTickets(Long user, Long queue, Long status) {

        if (user == null && queue == null && status == null) return ticketRepository.findAllTickets();
        else {
            Set<Ticket> tickets = new HashSet<>();
            if (user != null) {
                findElementOrThrowException(userRepository, user,
                        new UserNotFoundException("User with a provided ID does not exist."));
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByUserId(user), tickets);
            }
            if (queue != null) {
                findElementOrThrowException(queueRepository, queue,
                        new QueueNotFoundException("Queue with a provided ID does not exist."));
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByQueueId(queue), tickets);
            }
            if (status != null) {
                findElementOrThrowException(statusRepository, status,
                        new StatusNotFoundException("Status with a provided ID does not exist."));
                addElementsOrFindIntersection(ticketRepository.findAllTicketsByStatusId(status), tickets);
            }
            return tickets;
        }
    }

    public Ticket createNewTicket(NewTicketDTO DTO) {
        Status newStatus = statusRepository.findById(1L)
                .orElseThrow(() -> new StatusNotFoundException("New ticket status not initialized."));
        User general = userRepository.findById(1L)
                .orElseThrow(() -> new UserNotFoundException("General recipient not initialized."));
        return ticketRepository.save(mapNewTicket(DTO, newStatus, general));
    }

    public void setTicketStatus(Long id, Long status) {
        ticketRepository.updateTicketStatus(id, statusRepository.findById(status)
                .orElseThrow(() -> new StatusNotFoundException("Status with a given ID does not exist (yet).")));
    }

    public void setTicketQueue(Long id, Long queue) {
        ticketRepository.updateTicketQueue(id, queueRepository.findById(queue)
                .orElseThrow(() -> new QueueNotFoundException(("Queue with a given ID does not exist (yet)."))));
    }

    public void setTicketAssignee(Long id, Long user) {
        ticketRepository.updateTicketAssignee(id, userRepository.findById(user)
                .orElseThrow(() -> new UserNotFoundException("User with a provided ID does not exist.")));
    }

    public Queue createNewQueue(NewQueueDTO DTO) {
        return queueRepository.save(new Queue(DTO.getName()));
    }
}
