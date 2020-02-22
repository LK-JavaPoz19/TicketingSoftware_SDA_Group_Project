package pl.sda.ticketing_software_sda_gp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long conversationId;

    @OneToOne
    private Ticket ticket;

    public Conversation() {

    }

    public Conversation(Long conversationId, Ticket ticket) {
        this.conversationId = conversationId;
        this.ticket = ticket;
    }

    //    @OneToMany (
//            mappedBy = "conversation"
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<Message> messages;
}

