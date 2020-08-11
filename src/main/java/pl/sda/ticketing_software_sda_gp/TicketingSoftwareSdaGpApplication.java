package pl.sda.ticketing_software_sda_gp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("pl.sda.ticketing_software_sda_gp.repository")
public class TicketingSoftwareSdaGpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingSoftwareSdaGpApplication.class, args);
    }

}
