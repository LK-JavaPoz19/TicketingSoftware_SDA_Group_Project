package pl.sda.ticketing_software_sda_gp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.sda.ticketing_software_sda_gp.model.Queue;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;

import javax.transaction.Transactional;
import java.util.Set;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("SELECT t FROM Ticket t")
    Set<Ticket> findAllTickets();

    @Query("SELECT t FROM Ticket t WHERE t.user.userId = :userId")
    Set<Ticket> findAllTicketsByUserId(Long userId);

    @Query(value = "SELECT t from Ticket t WHERE t.queue.queueId = :queueId")
    Set<Ticket> findAllTicketsByQueueId(Long queueId);

    @Query("SELECT t FROM Ticket t WHERE t.ticketStatus.statusId = :statusId")
    Set<Ticket> findAllTicketsByStatusId(Long statusId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket t SET t.user = :user WHERE t.ticketId = :ticketId")
    void updateTicketAssignee(Long ticketId, User user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket t SET t.queue = :queue WHERE t.ticketId = :ticketId")
    void updateTicketQueue(Long ticketId, Queue queue);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket t SET t.ticketStatus = :status WHERE t.ticketId = :ticketId")
    void updateTicketStatus(Long ticketId, Status status);
}