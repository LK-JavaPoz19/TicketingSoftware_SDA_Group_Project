package pl.sda.ticketing_software_sda_gp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.Ticket;

import java.util.Set;

public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query("select m from Message m where m.conversation.conversationId = :conversationId and m.toUser is null")
    Set<Message> findAllByConversationIdIsAndToUserIsNull(Long conversationId);


}
