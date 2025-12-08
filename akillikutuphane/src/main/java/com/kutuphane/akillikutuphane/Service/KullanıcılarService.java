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

    public List<Kullanıcılar> getAll() {
        return kullanıcılarRepository.findAll();
    }

    public Kullanıcılar getById(Integer id) {
        return kullanıcılarRepository.findById(id).orElse(null);
    }

    public Kullanıcılar save(Kullanıcılar kullanıcı) {
        return kullanıcılarRepository.save(kullanıcı);
    }

    public Kullanıcılar update(Integer id, Kullanıcılar kullanıcıDetails) {
        return kullanıcılarRepository.findById(id).map(kullanıcı -> {
            kullanıcı.setKullanıcı_ad(kullanıcıDetails.getKullanıcı_ad());
            kullanıcı.setKullanıcı_soyad(kullanıcıDetails.getKullanıcı_soyad());
            kullanıcı.setEmail(kullanıcıDetails.getEmail());
            kullanıcı.setŞifre(kullanıcıDetails.getŞifre());
            return kullanıcılarRepository.save(kullanıcı);
        }).orElse(null);
    }

    public void delete(Integer id) {
        kullanıcılarRepository.deleteById(id);
    }
}
