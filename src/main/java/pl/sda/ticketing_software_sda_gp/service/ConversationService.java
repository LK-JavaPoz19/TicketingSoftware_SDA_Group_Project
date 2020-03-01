package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.NewTicketDTO;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.getGeneralRecipient;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, MessageRepository messageRepository,
                               UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Set<Conversation> findAllOrFilteredConversations(Long id) {
        if (id == null) return conversationRepository.findAllConversations();
        else return conversationRepository.findAllConversationsById(id);
    }

    public void addConversationAndFirstMessageForNewTicket(Ticket ticket, NewTicketDTO DTO) {
        Conversation conversation = conversationRepository.save(new Conversation(ticket));
        messageRepository.save(map(conversation, getGeneralRecipient(userRepository), DTO));
    }

    public Conversation getConversationsByTicketId(Long ticket) {
        return conversationRepository.findConversationsByTicketId(ticket);
    }
}
