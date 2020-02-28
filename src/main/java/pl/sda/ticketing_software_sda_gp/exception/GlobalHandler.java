package pl.sda.ticketing_software_sda_gp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<?> handleException(ResourceNotFoundException e, WebRequest request) {
        return handleExceptionInternal(e, buildExceptionResponse(e), null, HttpStatus.NOT_FOUND, request);
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
