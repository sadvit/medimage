package com.sadvit.to;

import com.sadvit.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by sadvit on 4/19/16.
 */
public class UserTO {

    @NotNull
    @Size(max = 64, min = 3)
    private String username;

    private String currentPassword;

    private String newPassword;

    private String name;

    private String surname;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    // refactoring all DTO/TO to converters

    public User convertToEntity() {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setAddress(address);
        return user;
    }


    public void createFromEntity(User entity) {
        name = entity.getName();
        surname = entity.getSurname();
        username = entity.getUsername();
        address = entity.getAddress();
    }

}
