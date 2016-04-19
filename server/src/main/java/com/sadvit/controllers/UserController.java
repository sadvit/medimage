package com.sadvit.controllers;

import com.sadvit.dto.UserInfo;
import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import com.sadvit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public void register(@RequestBody UserInfo user) {
		userService.register(user);
	}

}
