package pl.sda.ticketing_software_sda_gp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long idConversation;

//    @OneToMany (
//            mappedBy = "conversation"
////            cascade = CascadeType.ALL,
////            orphanRemoval = true
//    )
//    private List<Message> messages=new ArrayList<>();
//
//    @OneToOne(mappedBy = "conversation")
//    private Ticket ticket;


}

