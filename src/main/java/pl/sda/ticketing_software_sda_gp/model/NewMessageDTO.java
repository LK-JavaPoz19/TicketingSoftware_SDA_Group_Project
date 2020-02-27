package pl.sda.ticketing_software_sda_gp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMessageDTO {
    private Conversation conversation;
    private MessageType messageType;
    private User fromUser;
    private User toUser;
    private String body;
}
