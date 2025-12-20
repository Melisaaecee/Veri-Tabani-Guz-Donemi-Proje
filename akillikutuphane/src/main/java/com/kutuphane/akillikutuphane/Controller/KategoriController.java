package com.kutuphane.akillikutuphane.Controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kategori;
import com.kutuphane.akillikutuphane.Service.KategoriService;

@RestController
@RequestMapping("/api/kategori")
// @PreAuthorize("hasRole('ADMIN')")

public class KategoriController {

    private final KategoriService kategoriService;

    public KategoriController(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('KULLANICI','ADMIN')")
    public List<Kategori> tumKategoriler() {
        return kategoriService.tumunuGetir();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Kategori kategoriEkle(@RequestBody Kategori kategori) {
        return kategoriService.kaydet(kategori);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Kategori kategoriGuncelle(@PathVariable int id, @RequestBody Kategori kategori) {
        return kategoriService.guncelle(id, kategori);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void kategoriSil(@PathVariable int id) {
        kategoriService.sil(id);
    }
}
