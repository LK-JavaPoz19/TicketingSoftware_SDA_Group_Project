package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.*;

import java.time.LocalDateTime;

public class MessageMapper {

    public static Message map(Conversation conversation, NewMessageDTO DTO){
        return Message.builder()
                .conversation(conversation)
                .created(LocalDateTime.now())
                .messageType(DTO.getMessageType())
                .fromUser(DTO.getFromUser())
                .toUser(DTO.getToUser())
                .body(DTO.getBody())
                .build();
    }

    public static Message map(Conversation conversation, MessageType internalMessageType, User generalRecipient, NewTicketDTO DTO) {
        return Message.builder()
                .conversation(conversation)
                .created(LocalDateTime.now())
                .messageType(internalMessageType)
                .fromUser(DTO.getFromUser())
                .toUser(generalRecipient)
                .body(DTO.getBody())
                .build();
    }

    public static Message map(Conversation conversation, MessageType internalMessageType, User generalRecipient, NewMessageDTO DTO) {
        return Message.builder()
                .conversation(conversation)
                .created(LocalDateTime.now())
                .messageType(internalMessageType)
                .fromUser(DTO.getFromUser())
                .toUser(generalRecipient)
                .body(DTO.getBody())
                .build();
    }
}
