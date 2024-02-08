package com.example.demoproduct.model;

import java.time.LocalDate;

public class PhongTro {
   private int idPT;
   private int idThue;
   private String codeThue;
   private String nameThue;
   private String phone;
   private LocalDate dateThue;
   private String typeThue;
   private String about;

   public PhongTro() {
   }

   public PhongTro(int idPT, String codeThue, String nameThue, String phone, LocalDate dateThue, String typeThue, String about) {
      this.idPT = idPT;
      this.codeThue = codeThue;
      this.nameThue = nameThue;
      this.phone = phone;
      this.dateThue = dateThue;
      this.typeThue = typeThue;
      this.about = about;
   }

   public PhongTro(int idThue, String codeThue, String nameThue, String phone, LocalDate dateThue, String about) {
      this.idThue = idThue;
      this.codeThue = codeThue;
      this.nameThue = nameThue;
      this.phone = phone;
      this.dateThue = dateThue;
      this.about = about;
   }

   public int getIdPT() {
      return idPT;
   }

   public void setIdPT(int idPT) {
      this.idPT = idPT;
   }

   public int getIdThue() {
      return idThue;
   }

   public void setIdThue(int idThue) {
      this.idThue = idThue;
   }

   public String getCodeThue() {
      return codeThue;
   }

   public void setCodeThue(String codeThue) {
      this.codeThue = codeThue;
   }

   public String getNameThue() {
      return nameThue;
   }

   public void setNameThue(String nameThue) {
      this.nameThue = nameThue;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public LocalDate getDateThue() {
      return dateThue;
   }

   public void setDateThue(LocalDate dateThue) {
      this.dateThue = dateThue;
   }

   public String getTypeThue() {
      return typeThue;
   }

   public void setTypeThue(String typeThue) {
      this.typeThue = typeThue;
   }

   public String getAbout() {
      return about;
   }

   public void setAbout(String about) {
      this.about = about;
   }
}
