package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.NewTicketDTO;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;

public class TicketMapper {

    public static Ticket mapNewTicket(NewTicketDTO DTO, Status status, User user) {
        return Ticket.builder()
                .queue(DTO.getQueue())
                .user(user)
                .ticketStatus(status)
                .build();
    }
}
