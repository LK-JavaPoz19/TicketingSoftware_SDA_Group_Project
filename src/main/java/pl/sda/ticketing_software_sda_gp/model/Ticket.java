package pl.sda.ticketing_software_sda_gp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long ticketId;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @ManyToOne
    private Status ticketStatus;

    @NotNull
    @ManyToOne
    private Queue queue;
}