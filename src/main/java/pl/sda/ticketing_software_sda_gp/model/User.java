package pl.sda.ticketing_software_sda_gp.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Column(unique = true)
    @NotNull
    private String email;
    @Column(name="create_day",
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false, updatable = false)
    private LocalDateTime createDay;

    @ManyToOne(targetEntity = UserType.class)
    @NotNull
    private UserType userType;


}
