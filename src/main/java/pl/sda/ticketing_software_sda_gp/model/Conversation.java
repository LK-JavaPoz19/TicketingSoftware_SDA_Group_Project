package pl.sda.ticketing_software_sda_gp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long conversationId;

    @OneToOne
    private Ticket ticket;

    public Conversation(Long conversationId, Ticket ticket) {
        this.conversationId = conversationId;
        this.ticket = ticket;
    }
}

