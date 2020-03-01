package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.NewMessageDTO;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import java.time.LocalDateTime;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;

@Service
public class MessageService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository, ConversationService conversationService) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
    }

    public Message createNewMessageInConversation(Long id, NewMessageDTO DTO) {
        return messageRepository.save(map(
                conversationRepository.findById(id).orElse(null), DTO));
    }



    public void addNewInternalMessageInExistingConversation(NewMessageDTO messageDTO, Long id) {
        Conversation conversation = conversationService.findById(id);
        Message message = new Message(LocalDateTime.now(), conversation,
                messageDTO.getMessageType(),
                messageDTO.getFromUser(),
                messageDTO.getBody());
        messageRepository.save(message);
        Message debag=message;
    }
}
