package pl.sda.ticketing_software_sda_gp.model;

import lombok.Value;

@Value
public class NewMessageDTO {
    MessageType messageType;
    User fromUser;
    User toUser;
    String body;
}
