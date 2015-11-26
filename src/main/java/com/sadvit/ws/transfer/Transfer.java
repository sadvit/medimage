package com.sadvit.ws.transfer;

/**
 * Created by sadvit on 26.11.15.
 */
public abstract class Transfer {

    private String id;

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String toJSON();

}
