package com.example.demoproduct.services;

import com.example.demoproduct.model.BenhAn;
import com.example.demoproduct.repository.IBenhAnRepository;
import com.example.demoproduct.repository.ImlBenhAnRepository;

import java.util.List;

public class ImlBenhAnService implements IBenhAnService {
    IBenhAnRepository imlProductRepository = new ImlBenhAnRepository();
    @Override
    public List<BenhAn> findAll() {
        return imlProductRepository.findAll();
    }

    @Override
    public BenhAn findById(Integer id) {
        return imlProductRepository.findById(id);
    }

    @Override
    public void save(BenhAn productNew) {
        imlProductRepository.save(productNew);
    }

    @Override
    public void update(BenhAn productUpdate) {
        imlProductRepository.update(productUpdate);
    }

    @Override
    public void delete(Integer idDel) {
        imlProductRepository.delete(idDel);
    }

    @Override
    public List<BenhAn> findName(String name) {
        return imlProductRepository.findName(name);
    }
}
