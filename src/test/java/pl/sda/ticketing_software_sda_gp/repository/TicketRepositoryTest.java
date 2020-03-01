package pl.sda.ticketing_software_sda_gp.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.ticketing_software_sda_gp.model.Queue;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TicketRepositoryTest {

    @Autowired
    private TicketRepository sut;

    @Test
    public void shouldFindTicketByStatusId() {
        //given
        Long statusId = 1L;


        //when
        Set<Ticket> actual=sut.findAllByTicketStatusIs(statusId);

        //then
        assertThat(actual)
                .extracting(Ticket::getTicketStatus)
                .extracting(Status::getStatusId)
                .allSatisfy(id -> assertThat(id).isEqualTo(1L));

    }

    @Test
    public void shouldFindTicketByUserId() {
        //given
        Long userId = 1L;


        //when
        Set<Ticket> actual=sut.findAllByUserIs(userId);

        //then
        assertThat(actual)
                .extracting(Ticket::getUser)
                .extracting(User::getUserId)
                .allSatisfy(id -> assertThat(id).isEqualTo(1L));

    }

    @Test
    public void shouldFindTicketByQueueAndStatus() {
        //given
        Long queueId = 1L;
        Long statusId = 1L;


        //when
        Set<Ticket> actual=sut.findAllByQueueAndAndTicketStatusIs(queueId,statusId);

        //then
        assertThat(actual)
                .extracting(Ticket::getQueue)
                .extracting(Queue::getQueueId)
                .allSatisfy(id -> assertThat(id).isEqualTo(1L));
        assertThat(actual)
                .extracting(Ticket::getTicketStatus)
                .extracting(Status::getStatusId)
                .allSatisfy(id -> assertThat(id).isEqualTo(1L));

    }



}