package pl.sda.ticketing_software_sda_gp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public final class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userTypeId;

    @NotNull
    @Column(nullable = false, unique = true)
    private String userTypeName;

    @Builder
    public UserType(Long userTypeId,String userTypeName) {
        this.userTypeId=userTypeId;
        this.userTypeName = userTypeName;
    }
}
