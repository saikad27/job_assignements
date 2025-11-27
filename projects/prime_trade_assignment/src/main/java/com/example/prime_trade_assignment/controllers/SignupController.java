package com.example.prime_trade_assignment.controllers;

import com.example.prime_trade_assignment.dto.UserDTO;

import com.example.prime_trade_assignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class SignupController {

    private final UserService userService;
    SignupController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/signup")
    public String signUp(){
        System.out.println("Signup called");
        return "signup.html";
    }

    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        try {
            userService.processUser(userDTO);
            return ResponseEntity.ok(Map.of("message","User registered successfully"));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",e.getMessage()));
        }
    }
}
