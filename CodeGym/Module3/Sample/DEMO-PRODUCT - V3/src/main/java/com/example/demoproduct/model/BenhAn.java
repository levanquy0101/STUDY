package com.example.demoproduct.model;

import java.time.LocalDate;

public class BenhAn {
    private int idBa;
    private int idBn;
    private String codeBa;
    private String codeBn;
    private String nameBn;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String reason;

    public BenhAn() {
    }

    public BenhAn(String codeBa,int idBn, LocalDate dateIn, LocalDate dateOut, String reason) {
        this.idBn = idBn;
        this.codeBa = codeBa;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.reason = reason;
    }


    public int getIdBa() {
        return idBa;
    }

    public void setIdBa(int idBa) {
        this.idBa = idBa;
    }

    public String getCodeBa() {
        return codeBa;
    }

    public void setCodeBa(String codeBa) {
        this.codeBa = codeBa;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public BenhAn(int idBa, String codeBa, LocalDate dateIn, LocalDate dateOut, String reason) {
        this.idBa = idBa;
        this.codeBa = codeBa;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.reason = reason;
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
