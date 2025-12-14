package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kategori;
import com.kutuphane.akillikutuphane.Service.KategoriService;

@RestController
@RequestMapping("/api/kategori")
public class KategoriController {
    @Autowired
    private KategoriService kategoriService;

    @GetMapping
    public Iterable<Kategori> getAll() {
        return kategoriService.tumunuGetir();
    }

    @GetMapping("/{id}")
    public Kategori getById(@PathVariable Integer id) {
        return kategoriService.idyeGoreBul(id);
    }

    @PostMapping
    public Kategori create(@RequestBody Kategori kategori) {
        return kategoriService.kaydet(kategori);
    }

    @PutMapping("/{id}")
    public Kategori update(@PathVariable Integer id, @RequestBody Kategori kategoriDetails) {
        return kategoriService.guncelle(id, kategoriDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kategoriService.sil(id);
    }
}
