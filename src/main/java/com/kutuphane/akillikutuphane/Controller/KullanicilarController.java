package com.kutuphane.akillikutuphane.Controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Service.KullanicilarService;

@RestController
@RequestMapping("/api")
public class KullanicilarController {

    private final KullanicilarService service;

    public KullanicilarController(KullanicilarService service) {
        this.service = service;
    }


    // ===================== ADMIN =====================

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/kullanicilar")
    public List<Kullanicilar> tumKullanicilar() {
        return service.tumunuGetir();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/kullanicilar/{id}")
    public Kullanicilar kullaniciGetir(@PathVariable int id) {
        return service.idIleGetir(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/kullanicilar/{id}")
    public void sil(@PathVariable int id) {
        service.sil(id);
    }

    @GetMapping("/profil")
    public Kullanicilar profil(@AuthenticationPrincipal Kullanicilar user) {
        return user;
    }

}
