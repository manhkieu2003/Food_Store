package com.example.food.service;

import com.example.food.entity.Cart;
import com.example.food.entity.OrderAdress;
import com.example.food.entity.OrderRequest;
import com.example.food.entity.ProductOrder;
import com.example.food.repository.CartRepository;
import com.example.food.repository.ProductOrderRepository;
import com.example.food.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductOrderService {
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private CartRepository cartRepository;
    public void saveOrder(Integer userId, OrderRequest orderRequest) {
        List<Cart> cartList = cartRepository.findByUserId(userId);
        for(Cart cart : cartList) {
            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrderId(UUID.randomUUID().toString());
            productOrder.setOrderDate(new Date());
            productOrder.setProduct(cart.getProduct());
            productOrder.setPrice(cart.getProduct().getPprice());
            productOrder.setQuantity(cart.getQuantity());
            productOrder.setUser(cart.getUser());
            productOrder.setStatus(OrderStatus.IN_PROGRESS.name());
            productOrder.setPaymentType(orderRequest.getPaymentType());
            OrderAdress adress = new OrderAdress();
            adress.setFullname(orderRequest.getFullname());
            adress.setAddress(orderRequest.getAddress());
            adress.setEmail(orderRequest.getEmail());
            adress.setPhone(orderRequest.getPhone());
            productOrder.setOrderAdress(adress);
            productOrderRepository.save(productOrder);



        }

    }
    public List<ProductOrder> getOrdersByUser(Integer userId) {
     List<ProductOrder> orders=productOrderRepository.findByUserId(userId);
     return orders;
    }
    public List<ProductOrder> getAllOrder()
    {
        List<ProductOrder> allOrder=productOrderRepository.findAll();
        return allOrder;
    }
    public Boolean updateOrderStatus(Integer id,String status) {
        Optional<ProductOrder> orderId = productOrderRepository.findById(id);
        if(orderId.isPresent()) {
            ProductOrder order = orderId.get();
            order.setStatus(status);
            productOrderRepository.save(order);
            return true;
        }
        return false;

    }

    public void DeleteOrder(Integer id) {
         this.productOrderRepository.deleteById(id);
    }
}
