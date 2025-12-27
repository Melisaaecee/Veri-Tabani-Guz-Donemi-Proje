package com.kutuphane.akillikutuphane.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    private String kullaniciAd;
    private String kullaniciSoyad;

    @Email(message = "Lütfen geçerli bir mail adresi kullanın") // mail formatı doğru yazılmalı kontrol eder
    @NotBlank // boş mail reddedilir
    private String email;

    @NotBlank
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
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
