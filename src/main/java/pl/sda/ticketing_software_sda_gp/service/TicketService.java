package pl.sda.ticketing_software_sda_gp.service;



import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.NewTicket;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.repository.QueueRepository;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;
import pl.sda.ticketing_software_sda_gp.repository.TicketRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class TicketService {
    TicketRepository ticketRepository;
    StatusRepository statusRepository;
    QueueRepository queueRepository;

    public TicketService(TicketRepository ticketRepository, StatusRepository statusRepository, QueueRepository queueRepository) {
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
        this.queueRepository = queueRepository;
    }

    public Set<Ticket> findAllTickets(){return new HashSet<>(ticketRepository.findAll());}

    public Ticket createAndAddNewTicket(NewTicket newTicket) {
        Ticket ticket = new Ticket();
        Status status = new Status(1L, "NEW");
        ticket.setTicketStatus(status);
        ticket.setQueue(newTicket.getQueue());
        ticket.setUser(newTicket.getFromUser());
        ticketRepository.save(ticket);
        return ticket;
    }

}
