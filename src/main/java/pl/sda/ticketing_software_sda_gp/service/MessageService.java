package pl.sda.ticketing_software_sda_gp.service;


import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.service.ConversationService;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class MessageService {
    MessageRepository messageRepository;
    ConversationService conversationService;

    public MessageService(MessageRepository messageRepository, ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
    }

    public Set<Message> findAllMessages() {

        return new HashSet<>(messageRepository.findAll());
    }



    public void addMessage(Ticket ticket, ModelDTO modelDTO) {
        Conversation conversation=conversationService.addConversation(ticket);
        Message message = new Message(LocalDateTime.now(), conversation, modelDTO.getMessageType(),
                modelDTO.getFromUser(), modelDTO.getToUser(),
                modelDTO.getBody());
        messageRepository.save(message);
    }



    public void addNewInternalMessageInExistingConversation(ModelDTO messageDTO, Long id) {
        Conversation conversation = conversationService.findById(id);
        Message message = new Message(LocalDateTime.now(), conversation,
                messageDTO.getMessageType(),
                messageDTO.getFromUser(),
                messageDTO.getBody());
        messageRepository.save(message);
        Message debag=message;
    }
}
