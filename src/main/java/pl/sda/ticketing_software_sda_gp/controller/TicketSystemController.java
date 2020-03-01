package pl.sda.ticketing_software_sda_gp.controller;

import org.springframework.web.bind.annotation.*;
import pl.sda.ticketing_software_sda_gp.model.Conversation;
import pl.sda.ticketing_software_sda_gp.model.Message;
import pl.sda.ticketing_software_sda_gp.model.ModelDTO;
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
    public Set<Ticket> getAllTickets(){
        return ticketService.findAllTickets();
    }

    @CrossOrigin
    @GetMapping("/messages")
    public Set<Message> getAllMessages(){
        return messageService.findAllMessages();
    }

    @GetMapping("/conversations")
    public Set<Conversation> getAllConversations(){
        return conversationService.findAllConversations();
    }

    @CrossOrigin
    @GetMapping(value = "ticketsByStatus/{id}")
    public Set<Ticket> filterTicketsByStatus(@PathVariable Long id) {

        return ticketService.findAllTicketsByStatusId(id);
    }

    @GetMapping(value = "ticketsByUser/{id}")
    public Set<Ticket> filterTicketsByUser(@PathVariable Long id) {

        return ticketService.findAllTicketsByUserId(id);
    }

    @PostMapping("ticket/add")
    public void addNewTicketAndControllerAndMessage(@RequestBody ModelDTO modelDTO) {
        Ticket ticket = ticketService.createAndAddNewTicket(modelDTO);
        messageService.addMessage(ticket, modelDTO);
        System.out.println("New ticket, conversation and message were added");
    }

    @GetMapping(value = "ticketsByQueueByStatus/{idQueue}/{idStatus}")
    public Set<Ticket> filterTicketsByQueueAndStatus(@PathVariable Long idQueue,@PathVariable Long idStatus) {
        return ticketService.findAllTicketsByQueueAndStatus(idQueue,idStatus);
    }

    @PostMapping(value="conversation/add/message/{id}")
    public void addNewInternalMessageToConversation(@PathVariable Long id, @RequestBody ModelDTO messageDTO){
        messageService.addNewInternalMessageInExistingConversation(messageDTO,id);
        System.out.println("New internal message in conversation: "+id);
    }




}
