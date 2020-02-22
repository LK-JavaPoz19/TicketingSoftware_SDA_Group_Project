package pl.sda.ticketing_software_sda_gp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
import pl.sda.ticketing_software_sda_gp.model.User;
import pl.sda.ticketing_software_sda_gp.service.ConversationService;
import pl.sda.ticketing_software_sda_gp.service.MessageService;
import pl.sda.ticketing_software_sda_gp.service.TicketService;
import pl.sda.ticketing_software_sda_gp.service.UserService;

import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;
    private final TicketService ticketService;
    private final MessageService messageService;
    private final ConversationService conversationService;

    public UserController(UserService userService, TicketService ticketService, MessageService messageService, ConversationService conversationService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.messageService = messageService;
        this.conversationService = conversationService;
    }

    @GetMapping("/users")
    public Set<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
