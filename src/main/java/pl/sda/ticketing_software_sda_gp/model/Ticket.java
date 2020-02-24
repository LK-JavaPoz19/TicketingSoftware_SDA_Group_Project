package pl.sda.ticketing_software_sda_gp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
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

    @Builder
    private Ticket(User user, Status ticketStatus, Queue queue) {
        this.user = user;
        this.ticketStatus = ticketStatus;
        this.queue = queue;
    }
}