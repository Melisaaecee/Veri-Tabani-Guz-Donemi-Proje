package com.kutuphane.akillikutuphane.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "yazar")
public class Yazar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT için
    @Column(name = "yazar_id")
    private int yazar_id;

    @Column(name = "yazar_ad")
    private String yazar_ad;

    @Column(name = "yazar_soyad")
    private String yazar_soyad;

    public String getYazar_ad() {
        return yazar_ad;
    }

    public void setYazar_ad(String yazar_ad) {
        this.yazar_ad = yazar_ad;
    }

    public int getYazar_id() {
        return yazar_id;
    }

    public void setYazar_id(int yazar_id) {
        this.yazar_id = yazar_id;
    }

    public String getYazar_soyad() {
        return yazar_soyad;
    }

    public void setYazar_soyad(String yazar_soyad) {
        this.yazar_soyad = yazar_soyad;
    }

}
