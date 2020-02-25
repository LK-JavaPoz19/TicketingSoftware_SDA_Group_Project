package pl.sda.ticketing_software_sda_gp.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long messageId;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @NotNull
    @ManyToOne
    private Conversation conversation;

    @NotNull
    @ManyToOne
    private MessageType messageType;

    @NotNull
    @ManyToOne
    private User fromUser;

    @NotNull
    @ManyToOne
    private User toUser;

    @NotNull
    private String body;

    @Builder
    private Message(LocalDateTime created, Conversation conversation,
                   MessageType messageType, User fromUser, User toUser, String body) {
        this.created = created;
        this.conversation = conversation;
        this.messageType = messageType;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.body = body;
    }

}

