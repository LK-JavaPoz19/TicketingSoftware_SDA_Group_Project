package pl.sda.ticketing_software_sda_gp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;

import java.util.Set;


public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.userType.userTypeName = :userType")
    Set<User> findAllUsersByUserTypeName(String userType);

    @Query("SELECT t FROM User t")
    Set<User> findAllUsers();

    @Query("SELECT t FROM User t where t.userId = :id")
    User findByUserId(Long id);

}
