package com.example.prime_trade_assignment.service;


import com.example.prime_trade_assignment.dto.UserDTO;
import com.example.prime_trade_assignment.model.UserDetail;
import com.example.prime_trade_assignment.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public void processUser(UserDTO userDTO)throws SQLException {
        UserDetail newUser = new UserDetail();
        newUser.setUserId(null);
        newUser.setEmail(userDTO.getEmail());
        newUser.setUserName(userDTO.getUserName());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRegistrationDate(Date.valueOf(LocalDate.now()));
        newUser.setRegistrationTime(Time.valueOf(LocalTime.now()));
        userRepository.save(newUser);
    }
}
