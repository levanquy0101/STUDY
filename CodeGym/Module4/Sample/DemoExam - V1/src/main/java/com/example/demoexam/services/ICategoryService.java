package com.example.demoexam.services;

import com.example.demoexam.models.Category;
import com.example.demoexam.models.Product;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    void save(Category category);
    void deleteById(Long id);
    Category findById(Long id);
}
