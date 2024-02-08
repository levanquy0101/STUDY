package com.example.demoproduct.repository;

import com.example.demoproduct.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImlProductRepository implements IProductRepository {

    private static final String INSERT_PRODUCT_SQL = "insert into product( name, price, about) values (?,?,?)";
    private static final String SELECT_PRODUCT_BY_ID = "select id, name, price, about from product where id=?";
    private static final String SELECT_PRODUCT_BY_NAME = "select id, name, price, about from product where name like ?";
    private static final String SELECT_ALL_PRODUCT_SQL = "select * from product";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?";
    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?, price = ?, about = ? where id = ?";

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Product product;
        try {
            Statement statement = BaseRepository.getConnectionJavaToDB().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCT_SQL);
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setAbout(resultSet.getString("about"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return productList;
    }

    @Override
    public Product findById(Integer id) {
        try {
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setAbout(resultSet.getString("about"));
                return product;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return null;
    }

    @Override
    public void save(Product productNew) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(INSERT_PRODUCT_SQL);
//            preparedStatement.setInt(1,productNew.getId());
            preparedStatement.setString(1,productNew.getName());
            preparedStatement.setDouble(2,productNew.getPrice());
            preparedStatement.setString(3,productNew.getAbout());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product productUpdate) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(UPDATE_PRODUCT_SQL);
            preparedStatement.setInt(4,productUpdate.getId());
            preparedStatement.setString(1,productUpdate.getName());
            preparedStatement.setDouble(2,productUpdate.getPrice());
            preparedStatement.setString(3,productUpdate.getAbout());
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
    public List<Product> findName(String name) {
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_PRODUCT_BY_NAME);
            statement.setString(1,"%"+name+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setAbout(resultSet.getString("about"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }

        return productList;
    }
}