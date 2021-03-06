package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alvin
 **/
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There is no such mnemonics")
public class MnemonicsNotFoundException extends RuntimeException {
}
