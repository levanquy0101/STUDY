package com.example.demoproduct.repository;

import com.example.demoproduct.model.BenhNhan;

public interface IBenhNhanRepository {

    Integer save(BenhNhan benhNhanNew);

    void update(BenhNhan benhNhanUpdate);
}
