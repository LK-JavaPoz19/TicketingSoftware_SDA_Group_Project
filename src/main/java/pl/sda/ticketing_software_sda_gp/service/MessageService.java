package pl.sda.ticketing_software_sda_gp.service;



import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.UserNotFoundException;
import pl.sda.ticketing_software_sda_gp.mapper.MessageMapper;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }
    public Set<Message> findAllMessages(){return new HashSet<>(messageRepository.findAll());}

    public void addMessageAndConversation(Ticket ticket, TicketDTO ticketDTO) {
        Conversation conversation = new Conversation(ticket.getTicketId(), ticket);
        conversationRepository.save(conversation);
        Message message = userRepository.findById(1L)
                .map(userTo -> MessageMapper.map(conversation, ticketDTO, userTo))
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        messageRepository.save(message);
    }

}
