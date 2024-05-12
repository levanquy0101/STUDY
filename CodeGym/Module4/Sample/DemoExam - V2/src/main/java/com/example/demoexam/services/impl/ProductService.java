package com.example.demoexam.services.impl;

import com.example.demoexam.models.Product;
import com.example.demoexam.repositories.IProductRepository;
import com.example.demoexam.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findTopByOrderByCodeNameDesc() {
        return productRepository.findTopByOrderByCodeNameDesc();
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }
    @Override
    public List<Product> findByNameContainingOrCodeNameContaining(String nameKeyword, String codeKeyword) {
        return productRepository.findByNameContainingOrCodeNameContaining(nameKeyword,codeKeyword);
    }
    public Page<Product> findAllSearch(String nameKeyword, String codeKeyword, Pageable pageable) {
        return productRepository.findByNameContainingOrCodeNameContaining(nameKeyword, codeKeyword, pageable);
    }
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        return null;
    }

}
