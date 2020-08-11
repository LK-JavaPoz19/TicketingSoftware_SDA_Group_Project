package pl.sda.ticketing_software_sda_gp.repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.service.TicketService;
import javax.transaction.Transactional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TicketRepositoryTest {

    @Autowired
    private TicketRepository sut;
    @Autowired
    private TicketService ticketService;

    @Test
    public void shouldFindTicketByStatusId() {
        //given
        Long statusId = 1L;

        //when
        Set<Ticket> actual=sut.findAllTicketsByStatusId(statusId);

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
        Set<Ticket> actual=sut.findAllTicketsByUserId(userId);

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
        Set<Ticket> actual=sut.findAllTicketsByQueueAndAndTicketStatusIs(queueId,statusId);

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

    @Test
    @Transactional
    void shouldAddNewTicket() {
        //given
        int expected=sut.findAllTickets().size()+1;
        NewTicketDTO ticketDTO=new NewTicketDTO(
                Queue.builder().queueId(1L).build(),
                MessageType.builder().messageTypeId(1L).build(),
                User.builder().userId(2L).build(),
                "test");
        //when
        ticketService.addNewTicket(ticketDTO);
        Set<Ticket> actual = sut.findAllTickets();

        //then
        assertEquals(expected, actual.size());
    }

    @Test
    @Transactional
    void shouldUpdateStatusInTicket() {
        //given
        Long ticketId=1L;
        Long expectedStatusId=3L;
        //when
        ticketService.updateTicketStatus(ticketId,new Status(3L,"Test"));

        //then
        assertThat(sut.findById(ticketId)).get()
                .extracting(Ticket::getTicketStatus)
                .extracting(Status::getStatusId)
                .satisfies(id->assertThat(id).isEqualTo(expectedStatusId));
    }

    @Test
    @Transactional
    void shouldUpdateQueueInTicket() {
        //given
        Long ticketId=1L;
        Long expectedQueueId=2L;
        //when
        ticketService.updateTicketQueue(ticketId,new Queue(2L,"Test"));

        //then
        assertThat(sut.findById(ticketId)).get()
                .extracting(Ticket::getQueue)
                .extracting(Queue::getQueueId)
                .satisfies(id->assertThat(id).isEqualTo(expectedQueueId));
    }

    @Test
    @Transactional
    void shouldUpdateUserInTicket() {
        //given
        Long ticketId=1L;
        Long expectedUserId=2L;

        //when
        ticketService.updateTicketUser(ticketId,new User(2L,"name","password",new UserType(1L,"test"),true));

        //then
        assertThat(sut.findById(ticketId)).get()
                .extracting(Ticket::getUser)
                .extracting(User::getUserId)
                .satisfies(id->assertThat(id).isEqualTo(expectedUserId));
    }

    @Test
    @Transactional
    void shouldDeleteTicket() {
        //given
        Long ticketId=1L;
        int expected=sut.findAllTickets().size()-1;

        //when
        ticketService.deleteTicket(ticketId);
        Set<Ticket> actual = sut.findAllTickets();

        //then
        assertEquals(expected, actual.size());
    }
}