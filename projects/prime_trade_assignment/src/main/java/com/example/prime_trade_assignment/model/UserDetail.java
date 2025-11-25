package com.example.prime_trade_assignment.model;


import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name="user_details")
public class UserDetail {

    public UserDetail(Long userId, String userName, String email, String password, Date registrationDate, Time registrationTime) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long userId;

    private String userName;
    private String email;
    private String password;
    private Date registrationDate;
    private Time registrationTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Time getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Time registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", registrationTime=" + registrationTime +
                '}';
    }
}
