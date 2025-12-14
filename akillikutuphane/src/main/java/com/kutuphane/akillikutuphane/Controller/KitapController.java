package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kitap;
import com.kutuphane.akillikutuphane.Service.KitapService;

@RestController
@RequestMapping("/api/kitap")
public class KitapController {
    @Autowired
    private KitapService kitapService;

    @GetMapping
    public Iterable<Kitap> getAll() {
        return kitapService.tumunuGetir();
    }

    @GetMapping("/{id}")
    public Kitap getById(@PathVariable Integer id) {
        return kitapService.idyeGoreBul(id);
    }

    @PostMapping
    public Kitap create(@RequestBody Kitap kitap) {
        return kitapService.kaydet(kitap);
    }

    @PutMapping("/{id}")
    public Kitap update(@PathVariable Integer id, @RequestBody Kitap kitapDetails) {
        return kitapService.guncelle(id, kitapDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kitapService.sil(id);
    }
}
