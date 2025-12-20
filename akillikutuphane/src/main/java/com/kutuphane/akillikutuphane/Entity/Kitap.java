package com.kutuphane.akillikutuphane.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitap")
public class Kitap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitap_id")
    private int kitapId;

    @Column(name = "kitap_ad", nullable = false)
    private String kitapAd;

    @Column(name = "durum")
    private boolean durum;

    @Column(name = "stok_adedi")
    private int stokAdedi;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    @JsonIgnoreProperties("kitaplar")
    private Kategori kategori;

    @ManyToOne
    @JoinColumn(name = "yazar_id")
    @JsonIgnoreProperties("kitaplar") 
    private Yazar yazar;

    public Yazar getYazar() {
        return yazar;
    }

    public void setYazar(Yazar yazar) {
        this.yazar = yazar;
    }

    public int getKitapId() {
        return kitapId;
    }

    public void setKitapId(int kitapId) {
        this.kitapId = kitapId;
    }

    public String getKitapAd() {
        return kitapAd;
    }

    public void setKitapAd(String kitapAd) {
        this.kitapAd = kitapAd;
    }

    public boolean getDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public int getStokAdedi() {
        return stokAdedi;
    }

    public void setStokAdedi(int stokAdedi) {
        this.stokAdedi = stokAdedi;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

}
