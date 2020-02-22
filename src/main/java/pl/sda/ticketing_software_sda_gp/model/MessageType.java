package pl.sda.ticketing_software_sda_gp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MessageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long messageTypeId;

    @Column(nullable = false, unique = true)
    private String messageTypeName;
}
