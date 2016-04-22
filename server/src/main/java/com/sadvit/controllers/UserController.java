package com.sadvit.controllers;

import com.sadvit.to.UserTO;
import com.sadvit.models.User;
import com.sadvit.services.UserService;
import com.sadvit.validators.UserTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by vitaly.sadovskiy.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

    @Autowired
    private UserTOValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public UserTO getUser(@AuthenticationPrincipal User user) {
        return userService.getUser(user.getId());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserTO updateUser(@AuthenticationPrincipal User user, @Valid @RequestBody UserTO userTO, BindingResult result) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        return userService.updateUser(user.getId(), userTO);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
