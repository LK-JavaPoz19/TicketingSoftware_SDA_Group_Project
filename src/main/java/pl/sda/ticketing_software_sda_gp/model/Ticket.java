package pl.sda.ticketing_software_sda_gp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public final class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long ticketId;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Status ticketStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Queue queue;

    @Builder
    private Ticket(User user, Status ticketStatus, Queue queue) {
        this.user = user;
        this.ticketStatus = ticketStatus;
        this.queue = queue;
    }
}
