package com.example.demoproduct.services;

import com.example.demoproduct.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(Integer id);

    void save(Product productNew);

    void update(Product productUpdate);

    void delete(Integer idDel);

    List<Product> findName(String name);
}
