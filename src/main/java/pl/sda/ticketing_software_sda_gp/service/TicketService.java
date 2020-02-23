package pl.sda.ticketing_software_sda_gp.service;



import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.StatusNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.TicketDTO;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.repository.QueueRepository;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;
import pl.sda.ticketing_software_sda_gp.repository.TicketRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.TicketMapper.map;

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

    public Ticket createAndAddNewTicket(TicketDTO ticketDTO) {

        Optional<Status> newStatus = statusRepository.findById(1L);
        if (newStatus.isPresent()) {
            Ticket ticket = map(ticketDTO, newStatus.get());
            ticketRepository.save(ticket);
            return ticket;
        }
        throw new StatusNotFoundException("Status not found.");
    }
}
