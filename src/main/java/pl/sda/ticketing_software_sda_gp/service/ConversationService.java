package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.ElementNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageTypeRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.getGeneralRecipient;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.getInternalMessageType;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageTypeRepository messageTypeRepository;

    public ConversationService(ConversationRepository conversationRepository, MessageRepository messageRepository,
                               UserRepository userRepository, MessageTypeRepository messageTypeRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageTypeRepository = messageTypeRepository;
    }


    public Set<Conversation> findAll() {
        return conversationRepository.findAllConversations();
    }

    public Conversation findById(Long id) throws ElementNotFoundException {
        return conversationRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Not found"));

    }


    public void addConversationAndFirstMessageForNewTicket(Ticket ticket, NewMessageDTO DTO) {
        Conversation conversation = conversationRepository.save(new Conversation(ticket));
        messageRepository.save(map(conversation,getInternalMessageType(messageTypeRepository), getGeneralRecipient(userRepository),DTO));
    }

    public Conversation getConversationsByTicketId(Long ticket) {
        return conversationRepository.findConversationsByTicketId(ticket);
    }
}
