package com.example.demoproduct.services;

import com.example.demoproduct.model.BenhNhan;
import com.example.demoproduct.repository.IBenhNhanRepository;
import com.example.demoproduct.repository.ImlBenhNhanRepository;

public class ImlBenhNhanService implements IBenhNhanService{
    IBenhNhanRepository iBenhNhanRepository = new ImlBenhNhanRepository();

    @Override
    public Integer save(BenhNhan benhNhanNew) {
        return iBenhNhanRepository.save(benhNhanNew);
    }

    @Override
    public void update(BenhNhan benhNhanUpdate) {
        iBenhNhanRepository.update(benhNhanUpdate);
    }
}
