package com.weather.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.common.TokenExpiredException;
import com.weather.model.Token;
import com.weather.model.User;
import com.weather.service.UserService;
import com.weather.service.impl.UserServiceImpl;

@RestController
public class UserController {

    private UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public User registerUser(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "email",
            required = true) String email, @RequestParam(value = "pinCode", required = true) int pinCode) {
        User user = new User(name, email, pinCode);
        if (userService.isValid(user)) {
            userService.registerUser(user);
            return user;
        }
        return null;
    }

    @RequestMapping(value = "/getLogin", method = RequestMethod.PUT)
    public String registerUser(@RequestParam(value = "userId", required = true) long userId) {

        return userService.sendLoginEmail(userId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User registerUser(@RequestParam(value = "userId", required = true) long userId, @RequestParam(
            value = "token", required = true) String token) throws TokenExpiredException {
        return userService.openLoginEmail(new Token(userId));
    }


}
