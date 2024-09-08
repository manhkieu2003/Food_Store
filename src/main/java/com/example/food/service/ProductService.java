package com.example.food.service;

import com.example.food.entity.Product;
import com.example.food.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product>  getAllProduct(){
        List<Product> products = productRepository.findAll();
        return products;
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(int id){
         productRepository.deleteById(id);
    }
    public void updateproduct(Product p,int id)
    {
        p.setId(id);
        Optional<Product> optional = this.productRepository.findById(id);
        Product prod=optional.get();

        if(prod.getId()==id)
        {
            this.productRepository.save(p);
        }
    }
    // lấy 1 Product
    public Product getProductById(int id)
    {
        Optional<Product> optional = this.productRepository.findById(id);
        Product product=optional.get();
        return product;
    }
    // product theo category
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
