package pl.sda.ticketing_software_sda_gp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.TicketDTO;
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

    @GetMapping("/tickets")
    public Set<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }

    @GetMapping("/messages")
    public Set<Message> getAllMessages() {
        return messageService.findAllMessages();
    }

    @GetMapping("/conversations")
    public Set<Conversation> getAllConversations() {
        return conversationService.findAllConversations();
    }


    @PostMapping("ticket/add")
    public void addNewTicketAndControllerAndMessage(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.createAndAddNewTicket(ticketDTO);
        messageService.addMessageAndConversation(ticket, ticketDTO);
        System.out.println("New ticket, conversation and message were added");
    }

    @GetMapping("tickets/{id}/messages")
    public Set<Message> getAllMessagesForTicketByTicketId(@PathVariable Long id) {
        return messageService.getAllMessagesForTicketByTicketId(id);
    }

    @GetMapping("/tickets/queue")
    public ResponseEntity<Set<Ticket>> getAllTicketsFromQueueByQueueId(@RequestParam Long id) {
        Set<Ticket> tickets = ticketService.getAllTicketsFromQueueByQueueId(id);
        return (!tickets.isEmpty())
                ? new ResponseEntity<>(tickets, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }
}
