package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.*;

import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.findElementOrThrowException;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final MessageTypeRepository messageTypeRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, MessageRepository messageRepository,
                               MessageTypeRepository messageTypeRepository, UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.messageTypeRepository = messageTypeRepository;
        this.userRepository = userRepository;
    }

    public Set<Conversation> findAllOrFilteredConversations(Long id) {
        if (id == null) return conversationRepository.findAllConversations();
        else return conversationRepository.findAllConversationsById(id);
    }

    public void addConversationAndFirstMessageForNewTicket(Ticket ticket, NewTicketDTO DTO) {
        Conversation conversation = conversationRepository.save(new Conversation(ticket));
        MessageType messageType = findElementOrThrowException(messageTypeRepository, DTO.getMessageType(),
                "Message type with a provided ID does not exist.");
        User fromUser = findElementOrThrowException(userRepository, DTO.getFromUser(),
                "Message sender with a provided ID does not exist.");
        messageRepository.save(map(conversation, messageType, fromUser, ticket.getUser(), DTO.getBody()));
    }

    public Conversation getConversationsByTicketId(Long ticket) {
        return conversationRepository.findConversationsByTicketId(ticket);
    }
}
