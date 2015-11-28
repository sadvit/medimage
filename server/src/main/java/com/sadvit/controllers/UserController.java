package com.sadvit.controllers;

import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import com.sadvit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.loadAllUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public void register(@RequestParam String login, @RequestParam String pass) {
		userService.registerUser(login, pass);
	}

}
