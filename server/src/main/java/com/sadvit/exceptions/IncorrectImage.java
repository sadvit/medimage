package com.sadvit.exceptions;

/**
 * Created by sadvit on 4/21/16.
 */
public class IncorrectImage extends RuntimeException {

    public IncorrectImage(String imageId) {
        super("Can't find edge on image: " + imageId);
    }

}
