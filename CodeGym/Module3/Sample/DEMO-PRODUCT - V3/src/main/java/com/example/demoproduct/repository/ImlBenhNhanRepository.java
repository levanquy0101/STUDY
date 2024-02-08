package com.example.demoproduct.repository;

import com.example.demoproduct.model.BenhNhan;

import java.sql.*;

public class ImlBenhNhanRepository implements IBenhNhanRepository{
    private static final String INSERT_BENH_NHAN_SQL= "INSERT INTO benh_nhan (codeBn, nameBn) VALUES (?, ?)";
    private static final String UPDATE_BENH_NHAN = "UPDATE benh_nhan SET codeBn = ?, nameBn = ? WHERE idBn = ?";
    @Override
    public Integer save(BenhNhan benhNhan) {
        try {
            // Sử dụng truy vấn INSERT với RETURN_GENERATED_KEYS để lấy giá trị idBn
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(INSERT_BENH_NHAN_SQL, Statement.RETURN_GENERATED_KEYS);
            // Thiết lập các giá trị cho truy vấn INSERT
            preparedStatement.setString(1, benhNhan.getCodeBn());
            preparedStatement.setString(2, benhNhan.getNameBn());

            // Thực hiện truy vấn INSERT
            int rowsAffected = preparedStatement.executeUpdate();

            // Lấy giá trị idBn tăng tự động
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idBn = generatedKeys.getInt(1);
                        return idBn;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm mới bệnh nhân", e);
        }

        return -1; // Trả về -1 nếu có lỗi
    }

    @Override
    public void update(BenhNhan benhNhanUpdate) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(UPDATE_BENH_NHAN);
            preparedStatement.setInt(3,benhNhanUpdate.getIdBn());
            preparedStatement.setString(1,benhNhanUpdate.getCodeBn());
            preparedStatement.setString(2,benhNhanUpdate.getNameBn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
