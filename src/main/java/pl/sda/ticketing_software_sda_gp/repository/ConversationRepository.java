package pl.sda.ticketing_software_sda_gp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ticketing_software_sda_gp.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
}
