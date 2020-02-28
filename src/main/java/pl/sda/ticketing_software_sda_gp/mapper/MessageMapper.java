package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.*;

import java.time.LocalDateTime;

public class MessageMapper {

    public static Message map(Conversation conversation, MessageType messageType,
                              User fromUser, User general, String body){
        return Message.builder()
                .conversation(conversation)
                .created(LocalDateTime.now())
                .messageType(messageType)
                .fromUser(fromUser)
                .toUser(general)
                .body(body)
                .build();
    }
}
