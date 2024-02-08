package com.example.demo.services;

import com.example.demo.model.TypeProduct;
import com.example.demo.repository.ITypeProductRepo;
import com.example.demo.repository.ImlTypeProductRepo;

import java.util.List;

public class ImlTypeProductService implements ITypeProductService{
    ITypeProductRepo iTypeProductRepo = new ImlTypeProductRepo();
    @Override
    public List<TypeProduct> findAll() {
        return iTypeProductRepo.findAll();
    }
}
