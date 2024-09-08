package com.example.food.repository;

import com.example.food.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {
    List<ProductOrder> findByUserId(Integer userId);
}
