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
    @Column(name = "id", updatable = false, nullable = false)
    private Long idMessage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conversation_id")
    @NotNull
    private Conversation conversation;
    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User fromUser;
    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User toUser;
    @NotNull
    private String body;
    @Column(name="create_day",
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false, updatable = false)
    private LocalDateTime date;


}

