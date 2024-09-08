package com.example.food.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderRequest {
    private Integer id;
    private String fullname;
    private String address;
    private String email;
    private String phone;
    private String paymentType;

 }
