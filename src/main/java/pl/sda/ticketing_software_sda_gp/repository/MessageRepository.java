package pl.sda.ticketing_software_sda_gp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.ticketing_software_sda_gp.model.Message;

import java.util.Set;

public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query(value = "SELECT m from Message m")
    Set<Message> findAllMessages();

    @Query(value = "SELECT m from Message m WHERE m.conversation.conversationId = :conversation")
    Set<Message> findMessagesByConversationId(Long conversation);
}
