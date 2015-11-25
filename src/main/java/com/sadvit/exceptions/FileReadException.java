package com.sadvit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sadvit on 23.11.15.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileReadException extends RuntimeException {

    public FileReadException(String fileName) {
        super("File: \'" + fileName + "\' not found");
    }

}
