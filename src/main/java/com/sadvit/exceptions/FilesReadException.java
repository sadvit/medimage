package com.sadvit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sadvit on 23.11.15.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FilesReadException extends RuntimeException {

    public FilesReadException(String folderName) {
        super("Error read files from folder: \'" + folderName + "\'");
    }

}
