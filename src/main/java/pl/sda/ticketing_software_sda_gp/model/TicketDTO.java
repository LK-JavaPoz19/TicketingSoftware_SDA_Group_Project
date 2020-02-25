package pl.sda.ticketing_software_sda_gp.model;

import lombok.Getter;

@Getter
public class TicketDTO {

    public Queue queue;
    public MessageType messageType;
    public User fromUser;
    public String body;

}
