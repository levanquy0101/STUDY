package com.example.demo.repository;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImlProductRepository implements IProductRepository {

    private static final String INSERT_SQL = "insert into product( name,codeName, idType, price, dateSx, about) values (?,?,?,?,?,?)";
    private static final String SELECT_BY_ID = "select id, name,codeName, idType, price, dateSx, about from product where id=?";
    private static final String SELECT_BY_NAME = "select  id, name,codeName, typeName, price, dateSx, about from product join type_product on product.idType = type_product.idType where name like ? order by id;";
    private static final String SELECT_ALL_SQL = "select  id, name,codeName, typeName, price, dateSx, about from product join type_product on product.idType = type_product.idType order by id;";
    private static final String DELETE_SQL = "delete from product where id = ?";
    private static final String UPDATE_SQL = "update product set name = ?, idType = ?, price = ?, dateSx = ?, about = ?  where id = ?";

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productList = new ArrayList<>();
        ProductDTO product;
        try {
            Statement statement = BaseRepository.getConnectionJavaToDB().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                product = new ProductDTO();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCodeName(resultSet.getString("codeName"));
                product.setTypeName(resultSet.getString("typeName"));
                product.setDateSx(resultSet.getDate("dateSx").toLocalDate());
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
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCodeName(resultSet.getString("codeName"));
                product.setIdType(resultSet.getInt("idType"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDateSx(resultSet.getDate("dateSx").toLocalDate());
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
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(INSERT_SQL);
            preparedStatement.setString(1,productNew.getName());
            preparedStatement.setString(2,productNew.getCodeName());
            preparedStatement.setInt(3,productNew.getIdType());
            preparedStatement.setDouble(4,productNew.getPrice());
            preparedStatement.setDate(5, Date.valueOf(productNew.getDateSx()));
            preparedStatement.setString(6,productNew.getAbout());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product productUpdate) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1,productUpdate.getName());
//            preparedStatement.setString(2,productUpdate.getCodeName());
            preparedStatement.setInt(2,productUpdate.getIdType());
            preparedStatement.setDouble(3,productUpdate.getPrice());
            preparedStatement.setDate(4, Date.valueOf(productUpdate.getDateSx()));
            preparedStatement.setString(5,productUpdate.getAbout());
            preparedStatement.setInt(6,productUpdate.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int idDel) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnectionJavaToDB().prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, idDel);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductDTO> findName(String name) {
        List<ProductDTO> productList = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnectionJavaToDB().prepareStatement(SELECT_BY_NAME);
            statement.setString(1,"%"+name+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCodeName(resultSet.getString("codeName"));
                product.setTypeName(resultSet.getString("typeName"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDateSx(resultSet.getDate("dateSx").toLocalDate());
                product.setAbout(resultSet.getString("about"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return productList;
    }

    @Override
    public List<ProductDTO> sort(String sortType) {
        String SELECT_SORT_SQL = "select  id, name,codeName, typeName, price, dateSx, about from product join type_product on product.idType = type_product.idType order by ";
        SELECT_SORT_SQL+=sortType;

        List<ProductDTO> productList = new ArrayList<>();
        ProductDTO product;
        try {
            Statement statement = BaseRepository.getConnectionJavaToDB().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SORT_SQL);
            while (resultSet.next()) {
                product = new ProductDTO();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCodeName(resultSet.getString("codeName"));
                product.setTypeName(resultSet.getString("typeName"));
                product.setDateSx(resultSet.getDate("dateSx").toLocalDate());
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