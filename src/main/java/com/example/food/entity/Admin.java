package com.example.food.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
   private String adminName;
    private String adminEmail;
    @Value("1234")
    private String adminPassword;
    private String adminNumber;

}
