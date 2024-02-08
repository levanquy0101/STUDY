package com.example.demoproduct.model;

public class BenhNhan {
    private int idBn;
    private String codeBn;
    private String nameBn;

    public BenhNhan(String codeBn, String nameBn) {
        this.codeBn = codeBn;
        this.nameBn = nameBn;
    }

    public BenhNhan(int idBn, String codeBn, String nameBn) {
        this.idBn = idBn;
        this.codeBn = codeBn;
        this.nameBn = nameBn;
    }

    public int getIdBn() {
        return idBn;
    }

    public void setIdBn(int idBn) {
        this.idBn = idBn;
    }

    public String getCodeBn() {
        return codeBn;
    }

    public void setCodeBn(String codeBn) {
        this.codeBn = codeBn;
    }

    public String getNameBn() {
        return nameBn;
    }

    public void setNameBn(String nameBn) {
        this.nameBn = nameBn;
    }
}
