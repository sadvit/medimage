package com.sadvit.controllers;

import com.sadvit.services.UserService;
import com.sadvit.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody UserTO user) {
        userService.register(user);
    }

    @RequestMapping(value = "/login")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void login() {
    }

    @RequestMapping(value = "/logout")
    public void logout() {
    }

}
