package com.sadvit.dto;

import com.sadvit.models.Authority;
import com.sadvit.models.User;

import java.util.*;

/**
 * Created by sadvit on 4/19/16.
 */
public class UserInfo implements DTO<User> {

    private String username;

    private String currentPassword;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @Override
    public User convertToEntity() {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setAddress(address);
        return user;
    }

    @Override
    public void createFromEntity(User entity) {
        name = entity.getName();
        surname = entity.getSurname();
        username = entity.getUsername();
        address = entity.getAddress();
    }

}
