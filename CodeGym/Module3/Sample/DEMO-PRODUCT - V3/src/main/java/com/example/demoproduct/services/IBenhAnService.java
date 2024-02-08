package com.example.demoproduct.services;

import com.example.demoproduct.model.BenhAn;

import java.util.List;

public interface IBenhAnService {
    List<BenhAn> findAll();

    BenhAn findById(Integer id);

    void save(BenhAn productNew);

    void update(BenhAn productUpdate);

    void delete(Integer idDel);

    List<BenhAn> findName(String name);
}
