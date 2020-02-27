package pl.sda.ticketing_software_sda_gp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long messageId;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @NotNull
    @JsonBackReference
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Conversation conversation;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private MessageType messageType;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User fromUser;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User toUser;

    @NotNull
    @Column(nullable = false, updatable = false)
    private String body;

    @Builder
    public Message(LocalDateTime created, Conversation conversation,
                   MessageType messageType, User fromUser,
                   User toUser, String body) {
        this.created = created;
        this.conversation = conversation;
        this.messageType = messageType;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.body = body;
    }
}
