package com.example.food.service;

import com.example.food.entity.Cart;
import com.example.food.entity.Product;
import com.example.food.entity.User;
import com.example.food.repository.CartRepository;
import com.example.food.repository.ProductRepository;
import com.example.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    public Cart saveCart(Integer productId, Integer userId){
        User user = userRepository.findById(userId).get();

        Product product = productRepository.findById(productId).get();

Cart cartStatus = cartRepository.findByProductIdAndUserId(productId,userId);
Cart cart=null;
if(ObjectUtils.isEmpty(cartStatus)){ // cart trống chưa có cart
    cart = new Cart();
    cart.setUser(user);
    cart.setProduct(product);
    cart.setQuantity(1);
    cart.setTotalPrice(1*product.getPprice());
}else{ // đã có cart rôì
    cart = cartStatus;
    cart.setQuantity(cart.getQuantity()+1);
    cart.setTotalPrice(cart.getQuantity()*cart.getProduct().getPprice());
}
Cart saveCart = cartRepository.save(cart);


        return saveCart;
    }
    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }
    public List<Cart> getCartsByUser(Integer userId){
     List<Cart> carts= cartRepository.findByUserId(userId);
     Double totalAllPrice=0.0;
     List<Cart> updateCarts = new ArrayList<>();
     for(Cart cart:carts){
        Double totalPrice=(cart.getProduct().getPprice()*cart.getQuantity());
         cart.setTotalPrice(totalPrice);
         totalAllPrice +=totalPrice;
         cart.setTotalAllPrice(totalAllPrice);
         updateCarts.add(cart);
     }

        return carts ;

    }
    public Integer getCountCart(Integer userId)
    {
        Integer CountByUserId= cartRepository.countByUserId(userId);
        return CountByUserId;
    }
    public void DeleteCart(Integer id)
    {
        this.cartRepository.deleteById(id);
    }


    public void updateQuantity(String sy, Integer cid) {
        Cart cart=cartRepository.findById(cid).get();
        int updateQuantity;

        if(sy.equalsIgnoreCase("de"))
        {
            updateQuantity=cart.getQuantity()-1;
           if(updateQuantity<=0)
           {
               cartRepository.delete(cart);

           }else{
              cart.setQuantity(updateQuantity);
              cartRepository.save(cart);
           }
        }else{
            updateQuantity=cart.getQuantity()+1;
            cart.setQuantity(updateQuantity);
            cartRepository.save(cart);
        }


    }
}

