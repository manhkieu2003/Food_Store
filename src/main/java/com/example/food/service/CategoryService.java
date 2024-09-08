package com.example.food.service;

import com.example.food.entity.Category;
import com.example.food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category saveCategory(Category category) {
       return categoryRepository.save(category);
    }
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }
}
