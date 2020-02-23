package pl.sda.ticketing_software_sda_gp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.ticketing_software_sda_gp.model.Status;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
