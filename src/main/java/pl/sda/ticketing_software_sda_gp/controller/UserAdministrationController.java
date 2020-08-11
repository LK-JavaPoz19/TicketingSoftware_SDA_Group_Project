package pl.sda.ticketing_software_sda_gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;
import pl.sda.ticketing_software_sda_gp.service.UserService;

import java.security.Principal;
import java.util.Set;

@RestController
public class UserAdministrationController {

    private final UserService userService;

    public UserAdministrationController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<Set<User>> getAllOrFilteredTUsers(@RequestParam(required = false) String userType) {
        return new ResponseEntity<>(userService.getAllOrFilteredUsers(userType), HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }


    @GetMapping ("/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
