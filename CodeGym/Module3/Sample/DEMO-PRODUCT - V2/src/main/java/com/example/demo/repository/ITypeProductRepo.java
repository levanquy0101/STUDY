package com.example.demo.repository;

import com.example.demo.model.TypeProduct;

import java.util.List;

public interface ITypeProductRepo {
    List<TypeProduct> findAll();
}
