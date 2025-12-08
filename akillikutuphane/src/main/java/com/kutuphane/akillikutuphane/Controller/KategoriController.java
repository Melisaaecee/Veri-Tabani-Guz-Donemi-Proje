package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kategori;
import com.kutuphane.akillikutuphane.Repository.KategoriRepository;

@RestController
@RequestMapping("/api/kategori")
public class KategoriController {
    @Autowired
    private KategoriRepository kategoriRepository;

    @GetMapping
    public Iterable<Kategori> getAll() {
        return kategoriRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kategori getById(@PathVariable Integer id) {
        return kategoriRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Kategori create(@RequestBody Kategori kategori) {
        return kategoriRepository.save(kategori);
    }

    @PutMapping("/{id}")
    public Kategori update(@PathVariable Integer id, @RequestBody Kategori kategoriDetails) {
        return kategoriRepository.findById(id).map(kategori -> {
            kategori.setKategori_ad(kategoriDetails.getKategori_ad());
            return kategoriRepository.save(kategori);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kategoriRepository.deleteById(id);
    }
}
