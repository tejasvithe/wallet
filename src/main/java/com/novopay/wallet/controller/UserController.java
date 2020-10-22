package com.novopay.wallet.controller;

import com.novopay.wallet.dto.LoginDto;
import com.novopay.wallet.dto.UserDto;
import com.novopay.wallet.model.LoginToken;
import com.novopay.wallet.model.User;
import com.novopay.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/sign-up")
    public User signUp(@RequestBody UserDto userData) {

        return userService.signUp(userData);
    }

    @PostMapping(value = "/sign-in")
    public LoginToken signIn(@RequestBody LoginDto loginData) {


        return userService.signIn(loginData);
    }
}
