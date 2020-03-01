package pl.sda.ticketing_software_sda_gp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public final class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long statusId;

    @NotNull
    @Column(nullable =false, unique = true)
    private String statusName;
}
