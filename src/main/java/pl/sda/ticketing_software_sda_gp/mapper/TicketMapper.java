package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.*;

public class TicketMapper {

    public static Ticket map(Status status, User generalRecipient, NewTicketDTO DTO, Conversation newConversation) {
        return Ticket.builder()
                .ticketStatus(status)
                .queue(DTO.getQueue())
                .user(generalRecipient)
                .conversation(newConversation)
                .build();
    }
}
