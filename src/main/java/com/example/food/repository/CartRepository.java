package com.example.food.repository;

import com.example.food.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
   public Cart findByProductIdAndUserId(Integer productId, Integer userId );


   public Integer countByUserId(Integer userId);

  public List<Cart> findByUserId(Integer userId);
}
