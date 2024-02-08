package com.example.demoproduct.repository;

import com.example.demoproduct.model.PhongTro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImlPhongTroRepository implements IPhongTroRepository {

    private static final String INSERT_PRODUCT_SQL = "insert into phong_tro(nameThue, phone,dateThue, idThue, about) values (?,?,?,?,?)";
    private static final String SELECT_PRODUCT_BY_ID = "select id, name, price, about from product where id=?";
    private static final String SELECT_PRODUCT_BY_ = "select idPT, codeThue, nameThue, phone, dateThue, type_thue.typeThue, about from phong_tro join type_thue on type_thue.idThue = phong_tro.idThue where codeThue like ? OR nameThue like ? OR phone like ? order by idPT asc ";
    private static final String SELECT_ALL_PRODUCT_SQL = "select idPT, codeThue, nameThue, phone, dateThue, type_thue.typeThue, about from phong_tro join type_thue on type_thue.idThue = phong_tro.idThue order by idPT asc ";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM phong_tro WHERE codeThue = ?;";

    @Override
    public List<PhongTro> findAll() {
        List<PhongTro> productList = new ArrayList<>();
        PhongTro product;
        try {
            Statement statement = BaseRepository.getConnectionJavaToDB().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCT_SQL);
            int stt = 1;
            while (resultSet.next()) {
                product = new PhongTro();
                product.setIdPT(stt);
                product.setCodeThue(resultSet.getString("codeThue"));
                product.setNameThue(resultSet.getString("nameThue"));
                product.setPhone(resultSet.getString("phone"));
                product.setDateThue(resultSet.getDate("dateThue").toLocalDate());
                product.setTypeThue(resultSet.getString("typeThue"));
                product.setAbout(resultSet.getString("about"));
                productList.add(product);
                stt++;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return productList;
    }

    @Override
    public PhongTro findById(Integer id) {
        try {
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PhongTro product = new PhongTro();
                return product;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return null;
    }

    @Override
    public void save(PhongTro productNew) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(INSERT_PRODUCT_SQL);
//            preparedStatement.setString(1,productNew.getCodeThue());
            preparedStatement.setString(1,productNew.getNameThue());
            preparedStatement.setString(2,productNew.getPhone());
            preparedStatement.setDate(3, Date.valueOf(productNew.getDateThue()));
            preparedStatement.setInt(4,productNew.getIdThue());
            preparedStatement.setString(5,productNew.getAbout());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String codeDel) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(DELETE_PRODUCT_SQL);
            String[] codes = codeDel.split(",");
            for (int i = 0; i < codes.length; i++) {
                String code = codes[i].trim(); // Tránh khoảng trắng không mong muốn
                preparedStatement.setString(1, code);
                System.out.println(code);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<PhongTro> findName(String name) {
        List<PhongTro> productList = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_PRODUCT_BY_);
            statement.setString(1,"%"+name+"%");
            statement.setString(2,"%"+name+"%");
            statement.setString(3,"%"+name+"%");
            System.out.println(name);
            ResultSet resultSet = statement.executeQuery();
            int stt = 1;
            while (resultSet.next()) {
                PhongTro product = new PhongTro();
                product.setIdPT(stt);
                product.setCodeThue(resultSet.getString("codeThue"));
                product.setNameThue(resultSet.getString("nameThue"));
                product.setPhone(resultSet.getString("phone"));
                product.setDateThue(resultSet.getDate("dateThue").toLocalDate());
                product.setTypeThue(resultSet.getString("typeThue"));
                product.setAbout(resultSet.getString("about"));
                productList.add(product);
                stt++;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }

        return productList;
    }
}