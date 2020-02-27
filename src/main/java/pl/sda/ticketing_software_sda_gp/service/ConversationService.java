package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.MessageTypeNotFound;
import pl.sda.ticketing_software_sda_gp.exception.QueueNotFoundException;
import pl.sda.ticketing_software_sda_gp.exception.UserNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.NewTicketDTO;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.repository.*;

import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.mapInitialMessage;
import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.findElementOrThrowException;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final MessageTypeRepository messageTypeRepository;
    private final QueueRepository queueRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, MessageRepository messageRepository,
                               MessageTypeRepository messageTypeRepository, QueueRepository queueRepository,
                               UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.messageTypeRepository = messageTypeRepository;
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
    }

    public Set<Conversation> findAllOrFilteredConversations(Long id) {
        if (id == null) return conversationRepository.findAllConversations();
        else return conversationRepository.findAllConversationsById(id);
    }

    public void addConversationAndFirstMessageForNewTicket(Ticket ticket, NewTicketDTO DTO) {
        findElementOrThrowException(queueRepository, DTO.getQueue().getQueueId(),
                new QueueNotFoundException("Queue with a provided ID does not exist."));
        findElementOrThrowException(messageTypeRepository, DTO.getMessageType().getMessageTypeId(),
                new MessageTypeNotFound("Message type with a provided ID does not exist."));
        Conversation conversation = conversationRepository.save(new Conversation(ticket));

        findElementOrThrowException(userRepository, DTO.getFromUser().getUserId(),
                new UserNotFoundException("Message sender with a provided does not exist."));
        messageRepository.save(mapInitialMessage(DTO, conversation, ticket.getUser()));
    }

    public Conversation getConversationsByTicketId(Long ticket) {
        return conversationRepository.findConversationsByTicketId(ticket);
    }
}
