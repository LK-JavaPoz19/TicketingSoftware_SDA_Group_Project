package pl.sda.ticketing_software_sda_gp.exception;

import java.util.function.Supplier;

public class StatusNotFoundException extends RuntimeException  {
    public StatusNotFoundException(String message) {
        super(message);
    }
}