package pl.sda.ticketing_software_sda_gp.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
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
}

