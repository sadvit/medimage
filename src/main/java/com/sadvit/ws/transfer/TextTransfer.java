package com.sadvit.ws.transfer;

/**
 * Created by sadvit on 26.11.15.
 */
public class TextTransfer extends Transfer {

    private Object message;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toJSON() {
        return null;
    }

}
