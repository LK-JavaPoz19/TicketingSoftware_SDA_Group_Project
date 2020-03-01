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


    @Test
    @Transactional
    void addNewInternalMessageInExistingConversation() {
        //given
        Long id=1L;

        ModelDTO modelDTO=new ModelDTO(MessageType.builder().messageTypeId(1L).build(),User.builder().userId(2L).build(),"test");
        //when

        sut.addNewInternalMessageInExistingConversation(modelDTO,id);
        Set<Message> actual = messageRepository.findAllByConversationIdIsAndToUserIsNull(id);

        //then

        assertEquals(1, actual.size());

    }

}