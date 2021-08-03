package com.fallon.banking.web.controllers;


import com.fallon.banking.services.UserService;
import com.fallon.banking.web.dtos.AuthenticatedDTO;
import com.fallon.banking.web.dtos.LoginAccountDTO;
import com.fallon.banking.web.dtos.RegisterAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterAccountDTO registerAccountDTO ){

        try {
            userService.register(registerAccountDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/login")
    public AuthenticatedDTO loginUser(@RequestBody LoginAccountDTO loginAccountDTO){
        try {
            return userService.login(loginAccountDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
