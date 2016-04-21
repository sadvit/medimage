package com.sadvit.controllers;

import com.sadvit.dto.UserInfo;
import com.sadvit.services.UserService;
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

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public void register(@RequestBody UserInfo user) {
        userService.register(user);
    }

	@RequestMapping("/login")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public void login() {

    }

	@RequestMapping("/logout")
	public void logout() {

    }

}
