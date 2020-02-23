package pl.sda.ticketing_software_sda_gp.service;


import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.StatusNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.TicketDTO;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.repository.QueueRepository;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;
import pl.sda.ticketing_software_sda_gp.repository.TicketRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.TicketMapper.map;

@Service
public class TicketService {

    private final MessageService messageService;

    private final TicketRepository ticketRepository;
    private final StatusRepository statusRepository;
    private final QueueRepository queueRepository;

    public TicketService(MessageService messageService, TicketRepository ticketRepository,
                         StatusRepository statusRepository, QueueRepository queueRepository) {
        this.messageService = messageService;
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
        this.queueRepository = queueRepository;
    }

    public Set<Ticket> findAllTickets() {
        return new HashSet<>(ticketRepository.findAll());
    }

    public Ticket createTicket(TicketDTO ticketDTO) {
        Ticket dbTicket = statusRepository.findById(1L)
                .map(status -> map(ticketDTO, status))
                .orElseThrow(() -> new StatusNotFoundException("Status not found."));
        Ticket ticket = ticketRepository.save(dbTicket);
        messageService.addMessageAndConversation(ticket,ticketDTO);
        return ticket;
    }
}
