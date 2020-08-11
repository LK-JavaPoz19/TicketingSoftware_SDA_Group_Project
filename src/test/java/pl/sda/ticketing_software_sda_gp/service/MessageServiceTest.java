package pl.sda.ticketing_software_sda_gp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.MessageRepository;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MessageServiceTest {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MessageService sut;
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void ShouldAddNewInternalMessageInExistingConversation() {
        //given
        Long existingConversationId = 2L;
        Long generalRecipientId = 1L;
        int expected = messageRepository.findAllByConversationIdIsAndToUserIs(existingConversationId, generalRecipientId).size() + 1;
        NewMessageDTO messageDTO = new NewMessageDTO(MessageType.builder().messageTypeId(1L).build(), User.builder().userId(2L).build(), User.builder().userId(generalRecipientId).build(), "test");

        //when
        sut.addNewInternalMessageInExistingConversation(existingConversationId, messageDTO);
        Set<Message> actual = messageRepository.findAllByConversationIdIsAndToUserIs(existingConversationId, generalRecipientId);

        //then
        assertEquals(expected, actual.size());
    }

    @Test
    @Transactional
    void shouldAddNewMessageInExistingTicket() {
        //given
        Long existingConversationId = 2L;
        Long toUserId = 3L;
        int expected = messageRepository.findAllByConversationIdIsAndToUserIs(existingConversationId, toUserId).size() + 1;
        NewMessageDTO messageDTO = new NewMessageDTO(MessageType.builder().messageTypeId(2L).build(), User.builder().userId(2L).build(), User.builder().userId(toUserId).build(), "test");

        //when
        sut.addNewMessageInTicket(existingConversationId, messageDTO);
        Set<Message> actual = messageRepository.findAllByConversationIdIsAndToUserIs(existingConversationId, toUserId);

        //then
        assertEquals(expected, actual.size());
    }
}