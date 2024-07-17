package tn.telnet.workflow.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleProductNotFoundException
            (NotFoundException productNotFoundException)
    {
        GlobalException productException = new GlobalException(
                productNotFoundException.getMessage(),
                productNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(productException, HttpStatus.NOT_FOUND);
    }
}
