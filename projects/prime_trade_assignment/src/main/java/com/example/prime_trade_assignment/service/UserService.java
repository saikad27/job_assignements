package com.example.prime_trade_assignment.service;


import com.example.prime_trade_assignment.dto.LoginCredDTO;
import com.example.prime_trade_assignment.dto.UserDTO;
import com.example.prime_trade_assignment.exceptions.InvalidUserCredentialsException;
import com.example.prime_trade_assignment.model.UserDetail;
import com.example.prime_trade_assignment.repository.UserRepository;

import com.example.prime_trade_assignment.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.jwtUtil = jwtUtil;
    }
    public void processUser(UserDTO userDTO)throws SQLException {
        UserDetail newUser = new UserDetail();
        newUser.setUserId(null);
        newUser.setEmail(userDTO.getEmail());
        newUser.setUserName(userDTO.getUserName());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRegistrationDate(Date.valueOf(LocalDate.now()));
        newUser.setRegistrationTime(Time.valueOf(LocalTime.now()));
        newUser.setRole("USER");
        userRepository.save(newUser);
    }
    public ResponseEntity<?> authenticateUser(LoginCredDTO loginCredDTO) throws InvalidUserCredentialsException{
        UserDetail userDetail = userRepository.findByUserName(loginCredDTO.getUserName());
        String storedPassword = userDetail.getPassword();
        if(passwordEncoder.matches(loginCredDTO.getPassword(),storedPassword)){
            String token = jwtUtil.generateToken(loginCredDTO.getUserName());
            System.out.println(token);
            return ResponseEntity.ok(Map.of("token",token));
        }
        throw new InvalidUserCredentialsException();
    }
}
