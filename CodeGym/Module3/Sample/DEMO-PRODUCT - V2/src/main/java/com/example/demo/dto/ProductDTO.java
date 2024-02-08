package com.example.demo.dto;

import java.time.LocalDate;

public class ProductDTO {
    private int id;
    private String name;
    private String codeName;
    private String typeName;
    private double price;
    private LocalDate dateSx ;
    private String about;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, String codeName, String typeName, double price, LocalDate dateSx, String about) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.typeName = typeName;
        this.price = price;
        this.dateSx = dateSx;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateSx() {
        return dateSx;
    }

    public void setDateSx(LocalDate dateSx) {
        this.dateSx = dateSx;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
