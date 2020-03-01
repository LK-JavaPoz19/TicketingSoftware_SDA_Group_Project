package pl.sda.ticketing_software_sda_gp.model;

import lombok.Value;

@Value
public class NewTicketDTO {
    Queue queue;
    MessageType messageType;
    User fromUser;
    String body;
}