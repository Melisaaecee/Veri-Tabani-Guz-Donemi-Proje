package com.kutuphane.akillikutuphane.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "oduncalma_sistemi")
public class OduncAlmaSistemi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ödünc_id")
    private int oduncId;

    @Column(name = "alınan_tarih", nullable = false)
    private LocalDate alinanTarih;

    @Column(name = "planlanan_iade_tarihi", nullable = false)
    private LocalDate planlananIadeTarihi;

    @Column(name = "gercek_iade_tarihi")
    private LocalDate gercekIadeTarihi;

    @Column(name = "durum", nullable = false)
    private boolean durum;

    @ManyToOne
    @JoinColumn(name = "kitap_id", nullable = false)
    private Kitap kitap;

    @ManyToOne
    @JoinColumn(name = "kullanıcı_id", nullable = false)
    private Kullanicilar kullanici;

    // GETTER - SETTER

    public int getOduncId() {
        return oduncId;
    }

    public void setOduncId(int oduncId) {
        this.oduncId = oduncId;
    }

    public LocalDate getAlinanTarih() {
        return alinanTarih;
    }

    public void setAlinanTarih(LocalDate alinanTarih) {
        this.alinanTarih = alinanTarih;
    }

    public LocalDate getPlanlananIadeTarihi() {
        return planlananIadeTarihi;
    }

    public void setPlanlananIadeTarihi(LocalDate planlananIadeTarihi) {
        this.planlananIadeTarihi = planlananIadeTarihi;
    }

    public LocalDate getGercekIadeTarihi() {
        return gercekIadeTarihi;
    }

    public void setGercekIadeTarihi(LocalDate gercekIadeTarihi) {
        this.gercekIadeTarihi = gercekIadeTarihi;
    }

    public boolean getDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public Kitap getKitap() {
        return kitap;
    }

    public void setKitap(Kitap kitap) {
        this.kitap = kitap;
    }

    public Kullanicilar getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanicilar kullanici) {
        this.kullanici = kullanici;
    }
}
