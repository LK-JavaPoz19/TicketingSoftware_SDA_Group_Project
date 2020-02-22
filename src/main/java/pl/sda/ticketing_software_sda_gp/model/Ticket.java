package pl.sda.ticketing_software_sda_gp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long idTicket;
    @ManyToOne (targetEntity = Status.class)
    @NotNull
    private Status status;
    @ManyToOne (targetEntity = Queue.class)
    @NotNull
    private Queue queue;
    @OneToOne (fetch = FetchType.EAGER)
    @NotNull
    private Conversation conversation;


}
