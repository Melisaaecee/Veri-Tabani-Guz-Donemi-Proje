package com.kutuphane.akillikutuphane.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kategori")
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT için
    @Column(name = "kategoriid")
    private int kategoriid;

    @Column(name = "kategoriad")
    private String kategoriad;

    public String getKategori_ad() {
        return kategoriad;
    }

    public void setKategori_ad(String kategoriad) {
        this.kategoriad = kategoriad;
    }

    public int getKategori_id() {
        return kategoriid;
    }

    public void setKategori_id(int kategoriid) {
        this.kategoriid = kategoriid;
    }

}
