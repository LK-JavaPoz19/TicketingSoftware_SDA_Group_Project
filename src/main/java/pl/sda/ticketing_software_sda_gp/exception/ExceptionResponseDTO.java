package pl.sda.ticketing_software_sda_gp.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExceptionResponseDTO {
    String status;
    String reason;
    String type;
    String message;
}
