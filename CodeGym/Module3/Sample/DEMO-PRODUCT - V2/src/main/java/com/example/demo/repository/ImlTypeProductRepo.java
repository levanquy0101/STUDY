package com.example.demo.repository;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.TypeProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImlTypeProductRepo implements ITypeProductRepo{
    private static final String SELECT_ALL_SQL = "select * from type_product";
    @Override
    public List<TypeProduct> findAll() {
        List<TypeProduct> typeProductList = new ArrayList<>();
        TypeProduct typeProduct;
        try {
            Statement statement = BaseRepository.getConnectionJavaToDB().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                typeProduct = new TypeProduct();
                typeProduct.setIdType(resultSet.getInt("idType"));
                typeProduct.setTypeName(resultSet.getString("typeName"));
                typeProductList.add(typeProduct);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return typeProductList;
    }
}
