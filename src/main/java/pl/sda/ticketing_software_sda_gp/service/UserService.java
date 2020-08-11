package pl.sda.ticketing_software_sda_gp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.exception.ElementNotFoundException;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.sda.ticketing_software_sda_gp.service.ServiceUtility.addElementsOrFindIntersection;
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> getAllOrFilteredUsers(String userType) {
        log.info("getAllTickets");
        if (userType == null) return  userRepository.findAllUsers();
        else
            return userRepository.findAllUsersByUserTypeName(userType);
    }

    @Cacheable(cacheNames = "tickets")
    public User findById(Long id) throws ElementNotFoundException {
        log.info("userById");
        if(userRepository.findByUserId(id)!=null)
            return userRepository.findByUserId(id);
        else throw  new ElementNotFoundException("Element not found");
    }
}
