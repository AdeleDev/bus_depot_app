package company.busmanagement.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@Component
public class ValidationExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);
    private final static String RESPONSE_MESSAGE = "Validation exception - ";

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> toResponse(ValidationException exception) {
        logger.error(RESPONSE_MESSAGE + exception.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
