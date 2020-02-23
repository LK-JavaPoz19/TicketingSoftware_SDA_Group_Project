package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.*;

import java.time.LocalDateTime;

public class MessageMapper {

    private TicketDTO ticketDTO;

    public MessageMapper(TicketDTO ticketDTO) {
        this.ticketDTO = ticketDTO;
    }

    public static Message map(Conversation conversation, TicketDTO ticketDTO) {
        return Message.builder()
                .created(LocalDateTime.now())
                .conversation(conversation)
                .messageType(ticketDTO.getMessageType())
                .fromUser(ticketDTO.getFromUser())
                .toUser(ticketDTO.getToUser())
                .body(ticketDTO.getBody())
                .build();
    }
}

