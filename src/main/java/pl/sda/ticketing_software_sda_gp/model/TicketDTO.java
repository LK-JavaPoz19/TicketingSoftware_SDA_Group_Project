package pl.sda.ticketing_software_sda_gp.model;

//Klasa pomocnicza do parsowania danych z jsona (metoda addNewTicketAndControllerAndMessage())
public class TicketDTO {

    public Queue queue;
    public MessageType messageType;
    public User fromUser;
    public User toUser;
    public String body;

    public Queue getQueue() {
        return queue;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public User getFromUser() {
        return fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public String getBody() {
        return body;
    }
}
