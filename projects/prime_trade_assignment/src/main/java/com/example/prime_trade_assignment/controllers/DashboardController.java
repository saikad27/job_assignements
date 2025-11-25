package com.example.prime_trade_assignment.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashBoard(){
        return "dashboard.html";
    }
}
