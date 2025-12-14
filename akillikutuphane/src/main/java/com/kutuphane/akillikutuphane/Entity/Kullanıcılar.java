package com.kutuphane.akillikutuphane.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kullanicilar")
public class Kullanıcılar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT için
    @Column(name = "kullanıcı_id")
    private int kullanıcı_id;

    @Column(name = "kullanıcı_ad")
    private String kullanıcı_ad;

    @Column(name = "kullanıcı_soyad")
    private String kullanıcı_soyad;

    @Column(name = "email")
    private String email;

    @Column(name = "şifre")
    private String şifre;


    public String getKullanıcı_ad() {
        return kullanıcı_ad;
    }

    public void setKullanıcı_ad(String kullanıcı_ad) {
        this.kullanıcı_ad = kullanıcı_ad;
    }

    public String getKullanıcı_soyad() {
        return kullanıcı_soyad;
    }

    public void setKullanıcı_soyad(String kullanıcı_soyad) {
        this.kullanıcı_soyad = kullanıcı_soyad;
    }

    public int getKullanıcı_id() {
        return kullanıcı_id;
    }

    public void setKullanıcı_id(int kullanıcı_id) {
        this.kullanıcı_id = kullanıcı_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getŞifre() {
        return şifre;
    }

    public void setŞifre(String şifre) {
        this.şifre = şifre;
    }

}