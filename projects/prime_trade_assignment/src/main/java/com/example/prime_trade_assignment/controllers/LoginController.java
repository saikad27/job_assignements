package com.example.prime_trade_assignment.controllers;


import com.example.prime_trade_assignment.dto.LoginCredDTO;
import com.example.prime_trade_assignment.exceptions.InvalidUserCredentialsException;
import com.example.prime_trade_assignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {
    private final UserService userService;
    public LoginController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestBody LoginCredDTO loginCredDTO){
        System.out.println(loginCredDTO);
        try {
            return userService.authenticateUser(loginCredDTO);
        }catch(InvalidUserCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",e.getMessage()));        }
    }
}
