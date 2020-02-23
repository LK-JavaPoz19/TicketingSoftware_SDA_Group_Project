package pl.sda.ticketing_software_sda_gp.service;



import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;

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
        Message message = map(conversation, ticketDTO);

        messageRepository.save(message);
    }

}
