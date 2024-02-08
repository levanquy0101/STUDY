package com.example.demoproduct.services;

import com.example.demoproduct.model.PhongTro;

import java.util.List;

public interface IPhongTroService {
    List<PhongTro> findAll();

    PhongTro findById(Integer id);

    void save(PhongTro productNew);


    void delete(String codeDel);

    List<PhongTro> findName(String name);
}
