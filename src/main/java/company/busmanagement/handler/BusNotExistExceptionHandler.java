package company.busmanagement.handler;

import company.busmanagement.exception.BusNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class BusNotExistExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(BusNotExistExceptionHandler.class);

    @ExceptionHandler(value = BusNotExistException.class)
    public ResponseEntity<Object> toResponse(BusNotExistException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}