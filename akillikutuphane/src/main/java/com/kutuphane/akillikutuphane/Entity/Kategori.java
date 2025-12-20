package com.kutuphane.akillikutuphane.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "kategori")
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategori_id")
    private int kategoriId;

    @Column(name = "kategori_ad", nullable = false)
    private String kategoriAd;

    @OneToMany(mappedBy = "kategori")
    private List<Kitap> kitaplar;

    public int getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getKategoriAd() {
        return kategoriAd;
    }

    public void setKategoriAd(String kategoriAd) {
        this.kategoriAd = kategoriAd;
    }

    public List<Kitap> getKitaplar() {
        return kitaplar;
    }

    public void setKitaplar(List<Kitap> kitaplar) {
        this.kitaplar = kitaplar;
    }
}
