package com.example.demoproduct.services;

import com.example.demoproduct.model.Product;
import com.example.demoproduct.repository.IProductRepository;
import com.example.demoproduct.repository.ImlProductRepository;

import java.util.List;

public class ImlProductService implements IProductService {
    IProductRepository imlProductRepository = new ImlProductRepository();
    @Override
    public List<Product> findAll() {
        return imlProductRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return imlProductRepository.findById(id);
    }

    @Override
    public void save(Product productNew) {
        imlProductRepository.save(productNew);
    }

    @Override
    public void update(Product productUpdate) {
        imlProductRepository.update(productUpdate);
    }

    @Override
    public void delete(Integer idDel) {
        imlProductRepository.delete(idDel);
    }

    @Override
    public List<Product> findName(String name) {
        return imlProductRepository.findName(name);
    }
}
