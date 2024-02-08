package com.example.demo.model;

public class TypeProduct {
    private Integer idType;
    private String typeName;

    public TypeProduct() {
    }

    public TypeProduct(Integer idType, String typeName) {
        this.idType = idType;
        this.typeName = typeName;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
