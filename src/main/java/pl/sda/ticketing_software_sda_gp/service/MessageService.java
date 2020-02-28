package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.NewMessageDTO;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageTypeRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.findElementOrThrowException;

@Service
public class MessageService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final MessageTypeRepository messageTypeRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository,
                          MessageTypeRepository messageTypeRepository, UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.messageTypeRepository = messageTypeRepository;
        this.userRepository = userRepository;
    }

    public Set<Message> findAllOrFilteredMessages(Long conversation) {
        if (conversation == null) return messageRepository.findAllMessages();
        else return messageRepository.findMessagesByConversationId(conversation);
    }

    public Message createNewMessageInConversation(Long id, NewMessageDTO DTO) {
        return messageRepository.save(map(
                findElementOrThrowException(conversationRepository, id, "Conversation with a provided ID does not exist."),
                findElementOrThrowException(messageTypeRepository, DTO.getMessageType(), "a"),
                findElementOrThrowException(userRepository, DTO.getFromUser(), "a"),
                findElementOrThrowException(userRepository, 1L, "a"),
                DTO.getBody()));
    }
}
