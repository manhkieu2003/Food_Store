package com.example.food.repository;

import com.example.food.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
   public Admin findByAdminEmail(String email);
}
