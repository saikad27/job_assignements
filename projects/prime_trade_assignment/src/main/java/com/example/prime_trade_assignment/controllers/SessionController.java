package com.example.prime_trade_assignment.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpRequest;

@Controller
public class SessionController {

    @ResponseBody
    @GetMapping("/getSession")
    public String getSession(HttpServletRequest httpRequest){
        return "Http session : "+httpRequest.getSession().getId();
    }
}
