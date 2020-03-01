package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.model.*;

public class TicketMapper {

    private ModelDTO modelDTO;

    public TicketMapper(ModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }
    public static Ticket map(ModelDTO modelDTO, Status status) {
        return Ticket.builder()
                .queue(modelDTO.getQueue())
                .user(modelDTO.getFromUser())
                .ticketStatus(status)
                .build();
    }
}
