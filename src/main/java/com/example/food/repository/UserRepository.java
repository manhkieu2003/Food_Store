package com.example.food.repository;

import com.example.food.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


   public User findByUemail(String email);
}
