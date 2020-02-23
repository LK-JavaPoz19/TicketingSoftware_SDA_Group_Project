package pl.sda.ticketing_software_sda_gp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long ticketId;

    public Ticket() {
    }

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

    public Long getTicketId() {
        return ticketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Status ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
}