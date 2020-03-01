package pl.sda.ticketing_software_sda_gp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = PersistenceException.class)
    protected ResponseEntity<?> handleException(PersistenceException e, WebRequest request) {
        return handleExceptionInternal(e, buildExceptionResponse(e), null, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    private ExceptionResponseDTO buildExceptionResponse(RuntimeException e) {
        return ExceptionResponseDTO.builder()
                .status("FAILED")
                .reason("EXCEPTION")
                .type(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
}
