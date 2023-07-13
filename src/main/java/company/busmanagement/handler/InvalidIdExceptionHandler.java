package company.busmanagement.handler;

import company.busmanagement.exception.InvalidIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class InvalidIdExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(InvalidIdExceptionHandler.class);

    @ExceptionHandler(value = InvalidIdException.class)
    public ResponseEntity<Object> toResponse(InvalidIdException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}