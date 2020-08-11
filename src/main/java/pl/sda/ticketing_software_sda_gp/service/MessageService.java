package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.ElementNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.*;

import java.util.Optional;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.getGeneralRecipient;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.getInternalMessageType;

@Service
public class MessageService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    private final UserRepository userRepository;
    private final MessageTypeRepository messageTypeRepository;
    private final TicketRepository ticketRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository, ConversationService conversationService, UserRepository userRepository, MessageTypeRepository messageTypeRepository, TicketRepository ticketRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
        this.userRepository = userRepository;
        this.messageTypeRepository = messageTypeRepository;
        this.ticketRepository = ticketRepository;
    }

    public void addFirstMessageForNewTicket(Ticket ticket, NewTicketDTO DTO) {
        messageRepository.save(map(ticket.getConversation(),getInternalMessageType(messageTypeRepository), getGeneralRecipient(userRepository),DTO));
    }

    public Message addNewMessageInTicket(Long id, NewMessageDTO DTO) {
        return messageRepository.save(map(ticketRepository.findById(id)
                .orElseThrow(()->new ElementNotFoundException("Not found")).getConversation(), DTO));
    }

    public Message addNewInternalMessageInExistingConversation(Long id, NewMessageDTO DTO) {
        User generalRecipient=userRepository.findById(1L).orElse(null);
        MessageType internalMessageType=messageTypeRepository.findById(1L).orElse(null);
        return messageRepository.save(map(
                conversationRepository.findById(id).orElse(null),internalMessageType, generalRecipient, DTO));
    }

    public Set<Message> findAllMessages() {
        return messageRepository.findAllMessages();
    }

    public Message findById(Long idMessage) throws ElementNotFoundException{
        return messageRepository.findById(idMessage).orElseThrow(()->new ElementNotFoundException("Not found"));
    }
    public Set<Message> findAllMessagesByConversationId(Long id) throws ElementNotFoundException {
        if(messageRepository.findAllByConversationIdIs(id).isEmpty())
            throw new ElementNotFoundException("Not found");
        else return messageRepository.findAllByConversationIdIs(id);
    }


}
