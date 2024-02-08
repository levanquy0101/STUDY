package com.example.demo.services;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();

    Product findById(Integer id);

    void save(Product productNew);

    void update(Product productUpdate);

    void delete(Integer idDel);

    List<ProductDTO> findName(String name);

    List<ProductDTO> sort(String sortType);
}
