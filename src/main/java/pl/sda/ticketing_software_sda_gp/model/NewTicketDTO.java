package pl.sda.ticketing_software_sda_gp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTicketDTO {
    private Queue queue;
    private MessageType messageType;
    private User fromUser;
    private String body;
}