package pl.sda.ticketing_software_sda_gp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity()
public final class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long queueId;

    @NotNull
    @Column(nullable = false, unique = true)
    private String queueName;

    @Builder
    public Queue(Long queueId, String queueName) {
        this.queueId=queueId;
        this.queueName = queueName;
    }
}
