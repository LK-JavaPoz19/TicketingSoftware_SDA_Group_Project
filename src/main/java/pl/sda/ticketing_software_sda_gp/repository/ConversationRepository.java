package pl.sda.ticketing_software_sda_gp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.ticketing_software_sda_gp.model.Conversation;

import java.util.Set;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c")
    Set<Conversation> findAllConversations();

    @Query("SELECT c FROM Conversation c WHERE c.conversationId = :id")
    Set<Conversation> findAllConversationsById(Long id);

    @Query("SELECT c FROM Conversation c WHERE c.ticket.ticketId = :ticket")
    Conversation findConversationsByTicketId(Long ticket);

}
