package pl.sda.ticketing_software_sda_gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.service.ConversationService;
import pl.sda.ticketing_software_sda_gp.service.MessageService;
import pl.sda.ticketing_software_sda_gp.service.TicketService;

import java.util.Set;

@RestController
public class TicketSystemController {

    private final TicketService ticketService;
    private final MessageService messageService;
    private final ConversationService conversationService;

    public TicketSystemController(TicketService ticketService, MessageService messageService,
                                  ConversationService conversationService) {
        this.ticketService = ticketService;
        this.messageService = messageService;
        this.conversationService = conversationService;
    }

    @GetMapping(value = "/tickets")
    public ResponseEntity<Set<Ticket>> getAllOrFilteredTickets (@RequestParam(required = false) Long user,
                                                                @RequestParam(required = false) Long queue,
                                                                @RequestParam(required = false) Long status) {
        return new ResponseEntity<>(ticketService.getAllOrFilteredTickets(user, queue, status), HttpStatus.OK);
    }

    @GetMapping("/conversations")
    public ResponseEntity<Set<Conversation>> getAllOrFilteredConversations(@RequestParam(required = false) Long conversation) {
        return new ResponseEntity<>(conversationService.findAllOrFilteredConversations(conversation), HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<Set<Message>> getAllOrFilteredMessages(@RequestParam(required = false) Long conversation) {
        return new ResponseEntity<>(messageService.findAllOrFilteredMessages(conversation), HttpStatus.OK);
    }

    @GetMapping("/tickets/{id}/conversations")
    public ResponseEntity<Conversation> getConversationsByTicketId(@PathVariable Long id) {
        return new ResponseEntity<>(conversationService.getConversationsByTicketId(id), HttpStatus.OK);
    }

    @PostMapping("/queues") //TODO:zmienić na PUT i zwracać kod w zależności od tego czy istnieje czy nie
    public ResponseEntity<Queue> createNewQueue(@RequestBody Queue DTO) {
        return new ResponseEntity<>(ticketService.createNewQueue(DTO), HttpStatus.CREATED);
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createNewTicket(@RequestBody NewTicketDTO DTO) {
        return new ResponseEntity<>(ticketService.createNewTicket(DTO), HttpStatus.CREATED);
    }

    @PostMapping("/conversations/{id}")
    public ResponseEntity<Message> createNewMessageInConversation(@PathVariable Long id, @RequestBody NewMessageDTO DTO) {
        return new ResponseEntity<>(messageService.createNewMessageInConversation(id, DTO), HttpStatus.OK);
    }

    @PutMapping(value = "/tickets/{id}/set-queue-to-{queue}")
    public ResponseEntity<?> putTicketInQueue(@PathVariable Long id, @PathVariable Long queue) {
        ticketService.setTicketQueue(id, queue);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/tickets/{id}/set-status-to-{status}")
    public ResponseEntity<?> updateTicketStatus(@PathVariable Long id, @PathVariable Long status) {
        ticketService.setTicketStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/tickets/{id}/set-assignee-to-{user}")
    public ResponseEntity<?> updateTicketAssignee(@PathVariable Long id, @PathVariable Long user) {
        ticketService.setTicketAssignee(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
