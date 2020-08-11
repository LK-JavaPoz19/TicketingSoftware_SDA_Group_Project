package pl.sda.ticketing_software_sda_gp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.ticketing_software_sda_gp.exception.ElementNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.Queue;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;


import java.util.List;
import java.util.Set;


public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("SELECT t FROM Ticket t")
    Set<Ticket> findAllTickets();

    @Query("SELECT t FROM Ticket t WHERE t.ticketId = :userId")
    Ticket findByTicketId(Long userId);

    @Query(value = "SELECT t from Ticket t WHERE t.queue.queueId = :queueId")
    Set<Ticket> findAllTicketsByQueueId(Long queueId);

    @Query("SELECT t FROM Ticket t WHERE t.ticketStatus.statusId = :statusId")
    Set<Ticket> findAllTicketsByStatusId(Long statusId);

    @Query(value = "SELECT t from Ticket t WHERE t.queue.queueId = :queueId and t.ticketStatus.statusId = :statusId")
    Set<Ticket> findAllTicketsByQueueAndAndTicketStatusIs(Long queueId, Long statusId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket t SET t.user.userId = :userId WHERE t.ticketId = :ticketId")
    void updateTicketAssignee(Long ticketId, Long userId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket t SET t.queue.queueId = :queueId WHERE t.ticketId = :ticketId")
    void updateTicketQueue(Long ticketId, Long queueId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket t SET t.ticketStatus.statusId = :statusId WHERE t.ticketId = :ticketId")
    void updateTicketStatus(Long ticketId, Long statusId);

    @Query("SELECT t FROM Ticket t WHERE t.user.userId = :userId")
    Set<Ticket> findAllTicketsByUserId(Long user);



}