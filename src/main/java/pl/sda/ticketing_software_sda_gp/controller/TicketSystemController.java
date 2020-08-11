package pl.sda.ticketing_software_sda_gp.controller;

import org.springframework.http.HttpHeaders;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
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
    @GetMapping("/")
    public String gethello() {

        return "<h1>Hello!</h1>" +
                "<form action=\"/users\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Go to users (only for admin)\" \n" +
                "         name=\"Submit\" id=\"frm1_submit\" />\n" +
                "</form>"+
                "<form action=\"/users/1\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Go to users nr 1 (only for admin)\" \n" +
                "         name=\"Submit\" id=\"frm2_submit\" />\n" +
                "</form>"+
                "<form action=\"/tickets\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Go to tickets (clients&consultants)\" \n" +
                "         name=\"Submit\" id=\"frm3_submit\" />\n" +
                "</form>"+
                "<form action=\"/tickets/1\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Go to tickets nr 1 (clients&consultants)\" \n" +
                "         name=\"Submit\" id=\"frm4_submit\" />\n" +
                "</form>"+
                "<form action=\"/tickets/1/messages\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Go to messages in tickets nr 1 (clients&consultants)\" \n" +
                "         name=\"Submit\" id=\"frm5_submit\" />\n" +
                "</form>"+
                "<form action=\"/conversations\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Go to conversations (only consultants)\" \n" +
                "         name=\"Submit\" id=\"frm1_submit\" />\n" +
                "</form>"+
                "<form action=\"/logout\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Log out\" \n" +
                "         name=\"Submit\" id=\"frm6_submit\" />\n" +
                "</form>";
    }

    @CrossOrigin
    @GetMapping("/tickets")
    public ResponseEntity<Set<Ticket>> getAllOrFilteredTickets(@RequestParam(required = false) Long user,
                                                               @RequestParam(required = false) Long queue,
                                                               @RequestParam(required = false) Long status) {
        return new ResponseEntity<>(ticketService.getAllOrFilteredTickets(user, queue, status), HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/tickets/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.findById(id), HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping("/tickets/{id}/messages")
    public ResponseEntity<Set<Message>> getMessagesInTicket(@PathVariable Long id) {
        Long conversationId = ticketService.findById(id).getConversation().getConversationId();
        return new ResponseEntity<Set<Message>>(messageService.findAllMessagesByConversationId(conversationId), HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/tickets/{id}/messages/{messageId}")
    public ResponseEntity<Message> getMessagesByIdInTicket(@PathVariable Long messageId) {
        return new ResponseEntity<Message>(messageService.findById(messageId), HttpStatus.OK);

    }


    @CrossOrigin
    @GetMapping("/conversations")
    public ResponseEntity<Set<Conversation>> getAllConversations() throws SQLException {
        return new ResponseEntity<>(conversationService.findAll(), HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/messages")
    public Set<Message> getAllMessages() {
        return messageService.findAllMessages();
    }



    @CrossOrigin
    @PutMapping("/queues")
    public ResponseEntity<Queue> createNewQueue(@RequestBody Queue DTO) {
        return new ResponseEntity<>(ticketService.createNewQueue(DTO), HttpStatus.CREATED);
    }

////////////////////////POST/////////////////////////////////////////////////////////////////////////////////////

    @CrossOrigin
    @PostMapping(value = "/tickets")
    public ResponseEntity<Ticket> createNewTicket(@RequestBody NewTicketDTO ticketDTO) throws URISyntaxException{
        ticketService.addNewTicket(ticketDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("http://localhost:8080/tickets"));
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        //return new  ResponseEntity<>(ticketService.createNewTicket(ticketDTO), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping("/tickets/{id}/messages")
    public ResponseEntity<Message> createNewMessageInTicket(@PathVariable Long id, @RequestBody NewMessageDTO DTO) {
        return new ResponseEntity<>(messageService.addNewMessageInTicket(id, DTO), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "/tickets/{id}/internal-messages")
    public ResponseEntity<Message> addNewInternalMessageToConversation(@PathVariable Long id, @RequestBody NewMessageDTO messageDTO){
        Long conversationId = ticketService.findById(id).getConversation().getConversationId();
        return new ResponseEntity<>(messageService.addNewInternalMessageInExistingConversation(conversationId, messageDTO), HttpStatus.CREATED);
    }


    ///////////////////////PUT/////////////////////////////////////////////////////////////////////////////////////////

    @PutMapping(value = "/tickets/{id}/queue")
    public ResponseEntity<?> updateTicketQueue(@PathVariable Long id, @RequestBody Queue queue) {
        ticketService.updateTicketQueue(id, queue);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping (value = "/tickets/{id}/status")
    public ResponseEntity<?> updateTicketStatus(@PathVariable Long id, @RequestBody Status status) {
        ticketService.updateTicketStatus(id, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(value = "/tickets/{id}/user")
    public ResponseEntity<?> updateTicketAssignee(@PathVariable Long id, @RequestBody User user) {
        ticketService.updateTicketUser(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //////////////////////DELETE//////////////////////////////////////
    @CrossOrigin
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<?> deleteTickets(@PathVariable final Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    ///////////////////////////


}
