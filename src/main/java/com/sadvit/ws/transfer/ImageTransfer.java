package com.sadvit.ws.transfer;

/**
 * Created by sadvit on 26.11.15.
 */
public class ImageTransfer extends Transfer {

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toJSON() {
        return getId() + ";" + image;
    }

}
