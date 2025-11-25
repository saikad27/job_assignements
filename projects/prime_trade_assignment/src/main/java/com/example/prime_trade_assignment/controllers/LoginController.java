package com.example.prime_trade_assignment.controllers;


import com.example.prime_trade_assignment.dto.LoginCredDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestBody LoginCredDTO loginCredDTO){
        System.out.println("Processing user authentication");
        return null;
    }
}
