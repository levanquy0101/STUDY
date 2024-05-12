package com.example.demoexam.services;

import com.example.demoexam.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.management.loading.MLetContent;
import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findTopByOrderByCodeNameDesc();
    List<Product> findByNameContaining(String name);
    Page<Product> findAllSearch(String nameKeyword, String codeKeyword, Pageable pageable);
    List<Product> findByNameContainingOrCodeNameContaining(String nameKeyword, String codeKeyword);
    void save(Product product);
    void deleteById(Long id);
    Product findById(Long id);
    String saveImage(MultipartFile file) throws IOException;

    void deleteByIds(List<Long> ids);
}
