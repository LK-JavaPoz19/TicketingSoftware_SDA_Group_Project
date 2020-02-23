package pl.sda.ticketing_software_sda_gp.service;


import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.StatusNotFoundException;
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
    TicketRepository ticketRepository;
    StatusRepository statusRepository;
    QueueRepository queueRepository;

    public TicketService(TicketRepository ticketRepository, StatusRepository statusRepository, QueueRepository queueRepository) {
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
        this.queueRepository = queueRepository;
    }

    public Set<Ticket> findAllTickets() {
        return new HashSet<>(ticketRepository.findAll());
    }

    public Ticket createAndAddNewTicket(TicketDTO ticketDTO) {
        Ticket dbTicket = statusRepository.findById(1L)
                .map(status -> map(ticketDTO, status))
                .orElseThrow(() -> new StatusNotFoundException("Status not found."));
        return ticketRepository.save(dbTicket);
    }

    public Set<Ticket> getAllTicketsFromQueueByQueueId(Long id) {
        return ticketRepository.findAllTicketsFromQueueByQueueId(id);
    }

    public Set<Ticket> findAllTicketsByStatusId(Long statusId){

        return new HashSet<>(ticketRepository.findAllByTicketStatusIs(statusId));}


    public Set<Ticket> findAllTicketsByUserId(Long id) {
        return new HashSet<>(ticketRepository.findAllByUserIs(id));
    }

    public Set<Ticket> findAllTicketsByQueueAndStatus(Long idQueue, Long idStatus) {
        return new HashSet<>(ticketRepository.findAllByQueueAndAndTicketStatusIs(idQueue,idStatus));
    }
}

