package com.example.prime_trade_assignment.controllers;

import com.example.prime_trade_assignment.dto.UserDTO;
import com.example.prime_trade_assignment.model.UserDetail;
import com.example.prime_trade_assignment.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class SignupController {
    private final UserRepository userRepository;
    SignupController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping("/signup")
    public String signUp(){
        return "signup.html";
    }

    @ResponseBody
    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        userRepository.save(new UserDetail(null,userDTO.getUserName(),userDTO.getEmail(),userDTO.getPassword(), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now())));
        return userDTO;
    }
}
