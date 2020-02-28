package pl.sda.ticketing_software_sda_gp.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ExceptionResponseDTO {
    private String status;
    private String reason;
    private String type;
    private String message;
}
