package com.example.prime_trade_assignment.exceptions;



public class InvalidUserCredentialsException extends Exception{
    public InvalidUserCredentialsException(){
        super("User/Password is invalid");
    }
}
