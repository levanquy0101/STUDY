package com.example.demoproduct.repository;

import com.example.demoproduct.model.BenhAn;

import java.util.List;

public interface IBenhAnRepository {
    List<BenhAn> findAll();
    BenhAn findById(Integer id);

    void save(BenhAn productNew);

    void update(BenhAn productUpdate);

    void delete(int idDel);

    List<BenhAn> findName(String name);
}
