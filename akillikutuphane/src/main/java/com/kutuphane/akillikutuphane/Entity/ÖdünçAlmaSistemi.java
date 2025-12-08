package com.kutuphane.akillikutuphane.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "oduncalma_sistemi")
public class ÖdünçAlmaSistemi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT için
    @Column(name = "ödünç_id")
    private int ödünç_id;

    @Column(name = "alınan_tarih")
    private Date alınan_tarih;

    @Column(name = "durum")
    private boolean durum;

    @Column(name = "gerçek_iade_tarihi")
    private Date gerçek_iade_tarihi;

    @Column(name = "planlanan_iade_tarihi")
    private Date planlanan_iade_tarihi;

    public Date getAlınan_tarih() {
        return alınan_tarih;
    }

    public void setAlınan_tarih(Date alınan_tarih) {
        this.alınan_tarih = alınan_tarih;
    }

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public Date getGerçek_iade_tarihi() {
        return gerçek_iade_tarihi;
    }

    public void setGerçek_iade_tarihi(Date gerçek_iade_tarihi) {
        this.gerçek_iade_tarihi = gerçek_iade_tarihi;
    }

    public Date getPlanlanan_iade_tarihi() {
        return planlanan_iade_tarihi;
    }

    public void setPlanlanan_iade_tarihi(Date planlanan_iade_tarihi) {
        this.planlanan_iade_tarihi = planlanan_iade_tarihi;
    }

    public int getÖdünç_id() {
        return ödünç_id;
    }

    public void setÖdünç_id(int ödünç_id) {
        this.ödünç_id = ödünç_id;
    }

}
