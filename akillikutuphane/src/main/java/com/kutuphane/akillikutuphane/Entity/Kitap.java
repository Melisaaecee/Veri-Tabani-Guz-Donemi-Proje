package com.kutuphane.akillikutuphane.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitap")
public class Kitap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT için
    @Column(name = "kitap_id")
    private int kitap_id;

    @Column(name = "kitap_ad")
    private String kitap_ad;

    @Column(name = "durum")
    private boolean durum;

    @Column(name = "stok_adedi")
    private int stok_adedi;

    public int getKitap_id() {
        return kitap_id;
    }

    public void setKitap_id(int kitap_id) {
        this.kitap_id = kitap_id;
    }

    public String getKitap_ad() {
        return kitap_ad;
    }

    public void setKitap_ad(String kitap_ad) {
        this.kitap_ad = kitap_ad;
    }

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public int getStok_adedi() {
        return stok_adedi;
    }

    public void setStok_adedi(int stok_adedi) {
        this.stok_adedi = stok_adedi;
    }

}
