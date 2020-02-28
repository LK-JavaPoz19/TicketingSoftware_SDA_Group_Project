package pl.sda.ticketing_software_sda_gp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMessageDTO {
    private Long messageType;
    private Long fromUser;
    private Long toUser;
    private String body;
}
