package com.example.myblogtask.controllers;

import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.services.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class UserController {


    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody  UserDetails user){
        boolean registerUser = userService.registerUser(user);
        if(registerUser) {
            return "User " + user + " registered successfully";
        }
        return "User"+user+" not saved!!!";
    }

    @GetMapping("/login")
    public String loginUser(@RequestBody UserDetails user){
        UserDetails userDb = userService.loginUser(user.getEmail(),user.getPassword());
        if (userDb != null) {return  userDb + " logged in!!!";}
        return "User not in the Database!!!";
    }

}
