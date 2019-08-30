package io.mcc.customerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerIdException extends RuntimeException {
    public CustomerIdException(String message)  {
        super(message);
    }
}
