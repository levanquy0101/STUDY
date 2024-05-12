package com.example.demoexam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Mã sản phẩm cần phải nhập!")
    private String codeName;
    @Column
    @NotBlank(message = "Tên sản phẩm cần phải nhập!")
    private String name;
    @ManyToOne
    @JoinColumn
    private Category category;
    @Column
    private Double price;
    @Column
    @NotNull(message = "Ngày cần phải nhập!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSx;
    @Column
    @NotNull(message = "Ngày cần phải nhập!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateHh;
    @Column
    private String about;
    @AssertTrue(message = "Ngày kết thúc phải lớn hơn ngày bắt đầu!")
    public boolean isEndDateAfterStartDate() {
        if (dateHh == null || dateSx == null) {
            return true;
        }
        return dateHh.isAfter(dateSx);
    }
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateSx() {
        return dateSx;
    }

    public void setDateSx(LocalDate dateSx) {
        this.dateSx = dateSx;
    }

    public LocalDate getDateHh() {
        return dateHh;
    }

    public void setDateHh(LocalDate dateHh) {
        this.dateHh = dateHh;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
