package pl.sda.ticketing_software_sda_gp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.ticketing_software_sda_gp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
