package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alvin
 **/
@ResponseStatus(value = HttpStatus.CREATED, reason = "There is exist currency")
public class CurrencyIsExistException extends RuntimeException {
    public CurrencyIsExistException(String message) {
        super(message);
    }
}
