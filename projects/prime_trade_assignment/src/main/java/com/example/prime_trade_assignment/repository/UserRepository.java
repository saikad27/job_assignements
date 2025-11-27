package com.example.prime_trade_assignment.repository;


import com.example.prime_trade_assignment.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetail,Long> {

    public UserDetail findByUserName(String name);
}
