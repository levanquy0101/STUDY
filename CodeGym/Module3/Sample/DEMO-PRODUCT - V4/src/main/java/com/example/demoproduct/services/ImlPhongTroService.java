package com.example.demoproduct.services;

import com.example.demoproduct.model.PhongTro;
import com.example.demoproduct.repository.IPhongTroRepository;
import com.example.demoproduct.repository.ImlPhongTroRepository;

import java.util.List;

public class ImlPhongTroService implements IPhongTroService {
    IPhongTroRepository imlProductRepository = new ImlPhongTroRepository();
    @Override
    public List<PhongTro> findAll() {
        return imlProductRepository.findAll();
    }

    @Override
    public PhongTro findById(Integer id) {
        return imlProductRepository.findById(id);
    }

    @Override
    public void save(PhongTro productNew) {
        imlProductRepository.save(productNew);
    }

    @Override
    public void delete(String codeDel) {
        imlProductRepository.delete(codeDel);
    }

    @Override
    public List<PhongTro> findName(String name) {
        return imlProductRepository.findName(name);
    }
}
