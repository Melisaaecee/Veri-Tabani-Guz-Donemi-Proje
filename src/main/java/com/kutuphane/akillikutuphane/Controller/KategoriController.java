package com.kutuphane.akillikutuphane.Controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kategori;
import com.kutuphane.akillikutuphane.Service.KategoriService;

@RestController
@RequestMapping("/api/kategori")
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

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Kategori kategoriEkle(@RequestBody Kategori kategori) {
        return kategoriService.kaydet(kategori);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Kategori kategoriGuncelle(@PathVariable int id, @RequestBody Kategori kategori) {
        return kategoriService.guncelle(id, kategori);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void kategoriSil(@PathVariable int id) {
        kategoriService.sil(id);
    }
}
