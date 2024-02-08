package com.example.demoproduct.repository;

import com.example.demoproduct.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(Integer id);

    void save(Product productNew);

    void update(Product productUpdate);

    void delete(int idDel);

    List<Product> findName(String name);
}
