package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.*;

import java.time.LocalDateTime;

public class MessageMapper {

    public static Message mapInitialMessage(NewTicketDTO DTO, Conversation conversation, User general){
        return Message.builder()
                .conversation(conversation)
                .created(LocalDateTime.now())
                .messageType(DTO.getMessageType())
                .fromUser(DTO.getFromUser())
                .toUser(general)
                .body(DTO.getBody())
                .build();
    }

    public static Message mapNewMessage(NewMessageDTO DTO) {
        return Message.builder()
                .created(LocalDateTime.now())
                .conversation(DTO.getConversation())
                .messageType(DTO.getMessageType())
                .fromUser(DTO.getFromUser())
                .toUser(DTO.getToUser())
                .body(DTO.getBody()).build();
    }
}
