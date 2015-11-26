package com.sadvit.exceptions;

import org.springframework.web.socket.TextMessage;

/**
 * Created by sadvit on 26.11.15.
 */
public class TransferObjectParsingException extends RuntimeException {

    public TransferObjectParsingException(TextMessage textMessage) {
        super("Error parsing message: " + textMessage.getPayload());
    }
}
