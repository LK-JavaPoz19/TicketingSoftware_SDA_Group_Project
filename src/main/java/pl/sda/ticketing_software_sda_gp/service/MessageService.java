package pl.sda.ticketing_software_sda_gp.service;



import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class MessageService {
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public Set<Message> findAllMessages(){return new HashSet<>(messageRepository.findAll());}
}
