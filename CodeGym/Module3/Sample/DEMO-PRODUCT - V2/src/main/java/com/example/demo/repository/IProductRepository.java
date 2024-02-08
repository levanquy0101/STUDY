package com.example.demo.repository;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;

import java.util.List;

public interface IProductRepository {
    List<ProductDTO> findAll();
    Product findById(Integer id);

    void save(Product productNew);

    void update(Product productUpdate);

    void delete(int idDel);

    List<ProductDTO> findName(String name);

    List<ProductDTO> sort(String sortType);
}
