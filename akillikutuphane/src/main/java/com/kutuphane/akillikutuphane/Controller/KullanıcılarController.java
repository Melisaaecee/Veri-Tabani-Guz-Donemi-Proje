package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kullanıcılar;
import com.kutuphane.akillikutuphane.Repository.KullanıcılarRepository;

@RestController
@RequestMapping("/api/kullanicilar")
public class KullanıcılarController {
    @Autowired
    private KullanıcılarRepository kullanıcılarRepository;

    @GetMapping
    public Iterable<Kullanıcılar> getAll() {
        return kullanıcılarRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kullanıcılar getById(@PathVariable Integer id) {
        return kullanıcılarRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Kullanıcılar create(@RequestBody Kullanıcılar kullanıcı) {
        return kullanıcılarRepository.save(kullanıcı);
    }

    @PutMapping("/{id}")
    public Kullanıcılar update(@PathVariable Integer id, @RequestBody Kullanıcılar kullanıcıDetails) {
        return kullanıcılarRepository.findById(id).map(kullanıcı -> {
            kullanıcı.setKullanıcı_ad(kullanıcıDetails.getKullanıcı_ad());
            kullanıcı.setKullanıcı_soyad(kullanıcıDetails.getKullanıcı_soyad());
            kullanıcı.setEmail(kullanıcıDetails.getEmail());
            kullanıcı.setŞifre(kullanıcıDetails.getŞifre());
            return kullanıcılarRepository.save(kullanıcı);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kullanıcılarRepository.deleteById(id);
    }
}
