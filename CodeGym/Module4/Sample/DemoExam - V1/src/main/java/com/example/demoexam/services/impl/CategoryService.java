package com.example.demoexam.services.impl;

import com.example.demoexam.models.Category;
import com.example.demoexam.repositories.ICategoryRepository;
import com.example.demoexam.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Category findById(Long id) {
        return null;
    }
}
