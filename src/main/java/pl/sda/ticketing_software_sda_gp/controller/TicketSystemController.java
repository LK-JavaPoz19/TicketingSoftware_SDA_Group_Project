package pl.sda.ticketing_software_sda_gp.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.ticketing_software_sda_gp.model.*;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.Ticket;
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



    @CrossOrigin
    @GetMapping("/tickets")
    public ResponseEntity<Set<Ticket>> getAllOrFilteredTickets (@RequestParam(required = false) Long user,
                                                                @RequestParam(required = false) Long queue,
                                                                @RequestParam(required = false) Long status) {
        return new ResponseEntity<>(ticketService.getAllOrFilteredTickets(user, queue, status), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getAllOrFilteredTickets (@PathVariable Long id) {
        return new ResponseEntity<Ticket>(ticketService.findById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/conversations")
    public ResponseEntity<Set<Conversation>> getAllConversations() {
        return new ResponseEntity<>(conversationService.findAll(), HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/messages")
    public Set<Message> getAllMessages() {
        return messageService.findAllMessages();
    }

    @CrossOrigin
    @GetMapping("/tickets/{id}/conversation")
    public ResponseEntity<Conversation> getConversationsByTicketId(@PathVariable Long id) {
        return new ResponseEntity<>(conversationService.getConversationsByTicketId(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/queues")
    public ResponseEntity<Queue> createNewQueue(@RequestBody Queue DTO) {
        return new ResponseEntity<>(ticketService.createNewQueue(DTO), HttpStatus.CREATED);
    }
    @CrossOrigin
    @GetMapping(value = "ticketsByStatus/{id}")
    public Set<Ticket> filterTicketsByStatus(@PathVariable Long id) {
        return null;
    }


    @CrossOrigin
    @PostMapping("/add/ticket")
    public ResponseEntity<Ticket> createNewTicket(@RequestBody NewTicketDTO ticketDTO, @RequestBody NewMessageDTO messageDTO) {
        return new ResponseEntity<>(ticketService.createNewTicket(ticketDTO,messageDTO), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping("/conversations/{id}")
    public ResponseEntity<Message> createNewMessageInConversation(@PathVariable Long id, @RequestBody NewMessageDTO DTO) {
        return new ResponseEntity<>(messageService.createNewMessageInConversation(id, DTO), HttpStatus.OK);
    }

    @PutMapping(value = "/tickets/{id}/queue")
    public ResponseEntity<?> putTicketInQueue(@PathVariable Long id, @RequestBody Queue queue) {
        ticketService.setTicketQueue(id, queue);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/tickets/{id}/status")
    public ResponseEntity<?> updateTicketStatus(@PathVariable Long id, @RequestBody Status status) {
        ticketService.setTicketStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("ticket/add")
    public void addNewTicketAndControllerAndMessage(@RequestBody NewTicketDTO ticketDTO, @RequestBody NewMessageDTO messageDTO) {
        Ticket ticket = ticketService.createNewTicket(ticketDTO, messageDTO);
        conversationService.addConversationAndFirstMessageForNewTicket(ticket,messageDTO);
        System.out.println("New ticket, conversation and message were added");
    }

    @PutMapping(value = "/tickets/{id}/user")
    public ResponseEntity<?> updateTicketAssignee(@PathVariable Long id, @RequestBody User user) {
        ticketService.setTicketAssignee(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="add/message/in/conversation/{id}")
    public void addNewInternalMessageToConversation(@PathVariable Long id, @RequestBody NewMessageDTO messageDTO){
        messageService.addNewInternalMessageInExistingConversation(id,messageDTO);
        System.out.println("New internal message in conversation: "+id);
    }
}
