package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Kullanıcılar;
import com.kutuphane.akillikutuphane.Repository.KullanıcılarRepository;

@Service
public class KullanıcılarService {
    @Autowired
    private KullanıcılarRepository kullanıcılarRepository;

    public List<Kullanıcılar> tumunuGetir() {
        return kullanıcılarRepository.findAll();
    }

    public Kullanıcılar idyeGoreBul(Integer id) {
        return kullanıcılarRepository.findById(id).orElse(null);
    }

    public Kullanıcılar kaydet(Kullanıcılar kullanıcı) {
        if (kullanıcı == null || kullanıcı.getEmail() == null || kullanıcı.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email boş olamaz");
        }
        return kullanıcılarRepository.save(kullanıcı);
    }

    public Kullanıcılar guncelle(Integer id, Kullanıcılar kullanıcıDetails) {
        return kullanıcılarRepository.findById(id).map(kullanıcı -> {
            kullanıcı.setKullanıcı_ad(kullanıcıDetails.getKullanıcı_ad());
            kullanıcı.setKullanıcı_soyad(kullanıcıDetails.getKullanıcı_soyad());
            kullanıcı.setEmail(kullanıcıDetails.getEmail());
            kullanıcı.setŞifre(kullanıcıDetails.getŞifre());
            return kullanıcılarRepository.save(kullanıcı);
        }).orElse(null);
    }

    public void sil(Integer id) {
        kullanıcılarRepository.deleteById(id);
    }
}
