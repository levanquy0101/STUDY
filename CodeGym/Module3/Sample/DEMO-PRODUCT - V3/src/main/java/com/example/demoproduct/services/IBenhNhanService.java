package com.example.demoproduct.services;

import com.example.demoproduct.model.BenhNhan;

public interface IBenhNhanService {

    Integer save(BenhNhan benhNhanNew);

    void update(BenhNhan benhNhanUpdate);
}
