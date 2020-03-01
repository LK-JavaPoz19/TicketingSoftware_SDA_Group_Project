package pl.sda.ticketing_software_sda_gp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MessageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long messageTypeId;

    @Column(nullable = false, unique = true)
    private String messageTypeName;

    @Builder
    private MessageType(Long messageTypeId, String messageTypeName) {
        this.messageTypeId = messageTypeId;
        this.messageTypeName = messageTypeName;
    }
}
