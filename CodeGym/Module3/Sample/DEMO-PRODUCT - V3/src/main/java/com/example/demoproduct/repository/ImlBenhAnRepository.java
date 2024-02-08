package com.example.demoproduct.repository;

import com.example.demoproduct.model.BenhAn;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImlBenhAnRepository implements IBenhAnRepository {

    private static final String INSERT_BENH_AN_SQL = "insert into benh_an (codeBa, idBn, dateIn, dateOut, reason) values (?,?,?,?,?)";
    private static final String SELECT_PRODUCT_BY_ID = "select codeBa, benh_an.idBn, codeBn , nameBn , dateIn, dateOut, reason  from benh_an join benh_nhan on benh_an.idBn = benh_nhan.idBn  where idBa = ?;";
    private static final String SELECT_PRODUCT_BY_NAME = "select id, name, price, about from product where name like ?";
    private static final String SELECT_ALL_PRODUCT_SQL = "select * from benh_an join benh_nhan on benh_an.idBn = benh_nhan.idBn order by idBa";
    private static final String DELETE_PRODUCT_SQL = "delete from benh_an where idBa = ?";
    private static final String UPDATE_PRODUCT_SQL = "update benh_an set codeBa = ?, dateIn =?, dateOut =?, reason =? where idBa = ?";

    @Override
    public List<BenhAn> findAll() {
        List<BenhAn> benhAnList = new ArrayList<>();
        BenhAn benhAn;
        try {
            Statement statement = BaseRepository.getConnectionJavaToDB().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCT_SQL);
            while (resultSet.next()) {
                benhAn = new BenhAn();
                benhAn.setIdBa(resultSet.getInt("idBa"));
                benhAn.setCodeBa(resultSet.getString("codeBa"));
                benhAn.setCodeBn(resultSet.getString("codeBn"));
                benhAn.setNameBn(resultSet.getString("nameBn"));
                benhAn.setDateIn(LocalDate.parse(resultSet.getString("dateIn")));
                benhAn.setDateOut(LocalDate.parse(resultSet.getString("dateOut")));
                benhAn.setReason(resultSet.getString("reason"));
                benhAnList.add(benhAn);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return benhAnList;
    }

    @Override
    public BenhAn findById(Integer id) {
        try {
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BenhAn benhan = new BenhAn();
                benhan.setCodeBa(resultSet.getString("codeBa"));
                benhan.setIdBn(resultSet.getInt("idBn"));
                benhan.setCodeBn(resultSet.getString("codeBn"));
                benhan.setNameBn(resultSet.getString("nameBn"));
                benhan.setDateIn(resultSet.getDate("dateIn").toLocalDate());
                benhan.setDateOut(resultSet.getDate("dateOut").toLocalDate());
                benhan.setReason(resultSet.getString("reason"));
                return benhan;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return null;
    }

    @Override
    public void save(BenhAn productNew) {

        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(INSERT_BENH_AN_SQL);
//            preparedStatement.setInt(1,productNew.getId());
            preparedStatement.setString(1,productNew.getCodeBa());
            preparedStatement.setInt(2,productNew.getIdBn());
            preparedStatement.setDate(3, Date.valueOf(productNew.getDateIn()));
            preparedStatement.setDate(4, Date.valueOf(productNew.getDateOut()));
            preparedStatement.setString(5,productNew.getReason());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(BenhAn productUpdate) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(UPDATE_PRODUCT_SQL);
            preparedStatement.setInt(5,productUpdate.getIdBa());
            preparedStatement.setString(1,productUpdate.getCodeBa());;
            preparedStatement.setDate(2, Date.valueOf(productUpdate.getDateIn()));
            preparedStatement.setDate(3, Date.valueOf(productUpdate.getDateOut()));
            preparedStatement.setString(4,productUpdate.getReason());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int idDel) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(DELETE_PRODUCT_SQL);
            preparedStatement.setInt(1, idDel);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BenhAn> findName(String name) {
        List<BenhAn> productList = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_PRODUCT_BY_NAME);
            statement.setString(1,"%"+name+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BenhAn product = new BenhAn();
                product.setIdBa(resultSet.getInt("id"));
//                product.setName(resultSet.getString("name"));
//                product.setPrice(resultSet.getDouble("price"));
//                product.setAbout(resultSet.getString("about"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }

        return productList;
    }
}