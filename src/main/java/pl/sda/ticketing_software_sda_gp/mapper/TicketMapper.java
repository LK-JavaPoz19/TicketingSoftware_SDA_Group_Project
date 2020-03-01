package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.NewTicketDTO;
import pl.sda.ticketing_software_sda_gp.model.Status;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;

public class TicketMapper {

    public static Ticket map(Status status, User generalRecipient, NewTicketDTO DTO) {
        return Ticket.builder()
                .ticketStatus(status)
                .queue(DTO.getQueue())
                .user(generalRecipient)
                .build();
    }
}
