package pl.sda.ticketing_software_sda_gp.mapper;

import pl.sda.ticketing_software_sda_gp.exception.StatusNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.repository.StatusRepository;

import java.util.Optional;

public class TicketMapper {

    private TicketDTO ticketDTO;

    public TicketMapper(TicketDTO ticketDTO) {
        this.ticketDTO = ticketDTO;
    }
    public static Ticket map(TicketDTO ticketDTO, Status status) {
        return Ticket.builder()
                .queue(ticketDTO.getQueue())
                .user(ticketDTO.getFromUser())
                .ticketStatus(status)
                .build();
    }
}
