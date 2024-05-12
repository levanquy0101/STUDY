package com.example.demoexam.repositories;

import com.example.demoexam.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByNameContainingOrCodeNameContaining(String nameKeyword, String codeKeyword);
    Product findTopByOrderByCodeNameDesc();

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nameKeyword, '%')) ESCAPE '\\' OR LOWER(p.codeName) LIKE LOWER(CONCAT('%', :codeKeyword, '%')) ESCAPE '\\'")
    Page<Product> findByNameContainingOrCodeNameContaining(@Param("nameKeyword") String nameKeyword, @Param("codeKeyword") String codeKeyword, Pageable pageable);

}
