package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.Queue;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;

public class TicketMapper {

    public static Ticket map(Queue queue, Status status, User user) {
        return Ticket.builder()
                .queue(queue)
                .user(user)
                .ticketStatus(status)
                .build();
    }
}
