package pl.sda.ticketing_software_sda_gp.service;



import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class MessageService {
    MessageRepository messageRepository;
    ConversationRepository conversationRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
    }
    public Set<Message> findAllMessages(){return new HashSet<>(messageRepository.findAll());}

    public void addMessageAndConversation(Ticket ticket, TicketDTO ticketDTO) {
        Conversation conversation = new Conversation(ticket.getTicketId(), ticket);
        conversationRepository.save(conversation);
        Message message = new Message(LocalDateTime.now(), conversation, ticketDTO.getMessageType(),
                ticketDTO.getFromUser(), ticketDTO.getToUser(),
                ticketDTO.getBody());
        messageRepository.save(message);
    }

    public Set<Message> getAllMessagesForTicketByTicketId(Long id) {
        return messageRepository.findAllMessagesForTicketByTicketId(id);
    }
}
