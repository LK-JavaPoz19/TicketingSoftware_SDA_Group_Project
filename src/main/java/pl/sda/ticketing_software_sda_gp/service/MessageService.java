package pl.sda.ticketing_software_sda_gp.service;

import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.NewMessageDTO;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.mapper.MessageMapper.mapNewMessage;

@Service
public class MessageService {
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Set<Message> findAllOrFilteredMessages(Long conversation) {
        if (conversation == null) return messageRepository.findAllMessages();
        else return messageRepository.findMessagesByConversationId(conversation);
    }

    public Message createNewMessageInConversation(Long conversation, NewMessageDTO DTO) {
        return messageRepository.save(mapNewMessage(DTO));
    }
}
