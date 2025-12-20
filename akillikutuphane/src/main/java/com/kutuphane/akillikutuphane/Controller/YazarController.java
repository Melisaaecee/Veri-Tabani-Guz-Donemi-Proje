package com.kutuphane.akillikutuphane.Controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Yazar;
import com.kutuphane.akillikutuphane.Service.YazarService;

@RestController
@RequestMapping("/api/yazar")
// @PreAuthorize("hasRole('ADMIN')")
public class YazarController {

    private final YazarService yazarService;

    public YazarController(YazarService yazarService) {
        this.yazarService = yazarService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('KULLANICI','ADMIN')")
    public List<Yazar> tumYazarlar() {
        return yazarService.tumunuGetir();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Yazar yazarEkle(@RequestBody Yazar yazar) {
        return yazarService.kaydet(yazar);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Yazar yazarGuncelle(@PathVariable Integer id, @RequestBody Yazar yazar) {
        return yazarService.guncelle(id, yazar);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void yazarSil(@PathVariable Integer id) {
        yazarService.sil(id);
    }
}
