package com.example.demo.services;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.ImlProductRepository;

import java.util.List;

public class ImlProductService implements IProductService {
    IProductRepository imlProductRepository = new ImlProductRepository();
    @Override
    public List<ProductDTO> findAll() {
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
    public List<ProductDTO> findName(String name) {
        return imlProductRepository.findName(name);
    }

    @Override
    public List<ProductDTO> sort(String sortType) {
        return imlProductRepository.sort(sortType);
    }
}
