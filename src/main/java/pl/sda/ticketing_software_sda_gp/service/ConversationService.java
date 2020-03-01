package pl.sda.ticketing_software_sda_gp.service;


import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.ElementNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import java.util.HashSet;
import java.util.Set;


@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    public ConversationService(ConversationRepository conversationRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    public Set<Conversation> findAllConversations() {
        return new HashSet<>(conversationRepository.findAll());
    }


    public Conversation findById(Long id) {
        return conversationRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Conversation Not found"));
    }


    public Conversation addConversation(Ticket ticket) {
        Conversation conversation = new Conversation(ticket.getTicketId(), ticket);
        conversationRepository.save(conversation);
        return conversation;
    }

}

