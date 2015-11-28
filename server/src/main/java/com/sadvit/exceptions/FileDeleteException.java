package com.sadvit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sadvit on 23.11.15.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FileDeleteException extends RuntimeException {

    public FileDeleteException(String name) {
        super("Error delete file: \'" + name + "\'");
    }

}
