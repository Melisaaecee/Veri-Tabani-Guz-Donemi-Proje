package com.kutuphane.akillikutuphane.Controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kitap;
import com.kutuphane.akillikutuphane.Service.KitapService;

@RestController
@RequestMapping("/api/kitap")
public class KitapController {

    private final KitapService kitapService;

    public KitapController(KitapService kitapService) {
        this.kitapService = kitapService;
    }

    // TÜM KİTAPLAR
    @GetMapping
    @PreAuthorize("hasAnyRole('KULLANICI','ADMIN')")
    public List<Kitap> tumKitaplar() {
        return kitapService.tumunuGetir();
    }

    // KİTAP EKLE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Kitap kitapEkle(@RequestBody Kitap kitap, @RequestParam Integer kategoriId, @RequestParam Integer yazarId) {
        return kitapService.kaydet(kitap, kategoriId, yazarId);
    }

    // KİTAP GÜNCELLE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Kitap kitapGuncelle(@PathVariable Integer id, @RequestBody Kitap kitap, @RequestParam Integer yazarId) {
        return kitapService.guncelle(id, kitap, yazarId);
    }

    // KİTAP SİL
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void kitapSil(@PathVariable Integer id) {
        kitapService.sil(id);
    }

}
