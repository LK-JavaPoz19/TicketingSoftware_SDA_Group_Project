package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageTypeRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;

@Service
public class MessageService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    private final UserRepository userRepository;
    private final MessageTypeRepository messageTypeRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository, ConversationService conversationService, UserRepository userRepository, MessageTypeRepository messageTypeRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
        this.userRepository = userRepository;
        this.messageTypeRepository = messageTypeRepository;
    }

    public Message createNewMessageInConversation(Long id, NewMessageDTO DTO) {
        return messageRepository.save(map(
                conversationRepository.findById(id).orElse(null), DTO));
    }

    public Message addNewInternalMessageInExistingConversation(Long id, NewMessageDTO messageDTO) {
        User generalRecipient=userRepository.findById(1L).orElse(null);
        MessageType internalMessageType=messageTypeRepository.findById(1L).orElse(null);
        return messageRepository.save(map(
                conversationRepository.findById(id).orElse(null),internalMessageType, generalRecipient, messageDTO));
    }

    public Set<Message> findAllMessages() {
        return messageRepository.findAllMessages();
    }
}
