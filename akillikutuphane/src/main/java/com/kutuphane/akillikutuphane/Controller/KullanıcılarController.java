package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kullanıcılar;
import com.kutuphane.akillikutuphane.Service.KullanıcılarService;

@RestController
@RequestMapping("/api/kullanicilar")
public class KullanıcılarController {
    @Autowired
    private KullanıcılarService kullanıcılarService;

    @GetMapping
    public Iterable<Kullanıcılar> getAll() {
        return kullanıcılarService.tumunuGetir();
    }

    @GetMapping("/{id}")
    public Kullanıcılar getById(@PathVariable Integer id) {
        return kullanıcılarService.idyeGoreBul(id);
    }

    @PostMapping
    public Kullanıcılar create(@RequestBody Kullanıcılar kullanıcı) {
        return kullanıcılarService.kaydet(kullanıcı);
    }

    @PutMapping("/{id}")
    public Kullanıcılar update(@PathVariable Integer id, @RequestBody Kullanıcılar kullanıcıDetails) {
        return kullanıcılarService.guncelle(id, kullanıcıDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kullanıcılarService.sil(id);
    }
}
