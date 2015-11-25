package com.sadvit.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping("/login")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public void login() {}

	@RequestMapping("/logout")
	public void logout() {}

	@RequestMapping("/register")
	public void register() {}

}
