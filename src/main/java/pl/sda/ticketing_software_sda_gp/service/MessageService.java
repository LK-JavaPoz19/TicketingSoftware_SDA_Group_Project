package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.NewMessageDTO;
import pl.sda.ticketing_software_sda_gp.repository.ConversationRepository;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.map;

@Service
public class MessageService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    public Message createNewMessageInConversation(Long id, NewMessageDTO DTO) {
        return messageRepository.save(map(
                conversationRepository.findById(id).orElse(null), DTO));
    }
}
