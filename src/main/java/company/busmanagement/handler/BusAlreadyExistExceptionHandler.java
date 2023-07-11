package company.busmanagement.handler;

import company.busmanagement.exception.BusAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Component
public class BusAlreadyExistExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(BusAlreadyExistExceptionHandler.class);

    @ExceptionHandler(value = BusAlreadyExistException.class)
    public ResponseEntity<Object> toResponse(BusAlreadyExistException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}