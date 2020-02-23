package pl.sda.ticketing_software_sda_gp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.ticketing_software_sda_gp.model.Ticket;

import java.util.Set;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query(value = "SELECT t from Ticket t WHERE t.queue.queueId = :id")
    Set<Ticket> findAllTicketsFromQueueByQueueId(Long id);
}