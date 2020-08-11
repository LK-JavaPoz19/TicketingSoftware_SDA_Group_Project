package pl.sda.ticketing_software_sda_gp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
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

    @NotNull
    @OneToOne
    private Conversation conversation;

    @Builder
    public Ticket(User user, Status ticketStatus, Queue queue, Conversation conversation) {
        this.user = user;
        this.ticketStatus = ticketStatus;
        this.queue = queue;
        this.conversation = conversation;
    }


}


