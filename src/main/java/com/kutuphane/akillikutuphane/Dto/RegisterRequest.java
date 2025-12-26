package com.kutuphane.akillikutuphane.Dto;

public class RegisterRequest {

    private String kullaniciAd;
    private String kullaniciSoyad;
    private String email;
    private String sifre;

    public String getKullaniciAd() {
        return kullaniciAd;
    }

    public void setKullaniciAd(String kullaniciAd) {
        this.kullaniciAd = kullaniciAd;
    }

    public String getKullaniciSoyad() {
        return kullaniciSoyad;
    }

    public void setKullaniciSoyad(String kullaniciSoyad) {
        this.kullaniciSoyad = kullaniciSoyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
