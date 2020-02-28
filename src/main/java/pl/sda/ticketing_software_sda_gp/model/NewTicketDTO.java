package pl.sda.ticketing_software_sda_gp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTicketDTO {
    private Long queue;
    private Long messageType;
    private Long fromUser;
    private String body;
}