package pl.sda.ticketing_software_sda_gp.service;


import org.springframework.stereotype.Service;
import pl.sda.ticketing_software_sda_gp.model.User;
import pl.sda.ticketing_software_sda_gp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> getAllUsers(){
        return new HashSet<>(userRepository.findAll());
    }
}
