package pl.sda.ticketing_software_sda_gp.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(String message) {
        super(message);
    }
}