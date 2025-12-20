package com.kutuphane.akillikutuphane.Controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Entity.OduncAlmaSistemi;
import com.kutuphane.akillikutuphane.Service.OduncAlmaSistemiService;

@RestController
@RequestMapping("/api/odunc")
public class OduncAlmaSistemiController {

    private final OduncAlmaSistemiService oduncService;

    public OduncAlmaSistemiController(OduncAlmaSistemiService oduncService) {
        this.oduncService = oduncService;
    }

    // KİTAP ÖDÜNÇ AL
    @PreAuthorize("hasRole('KULLANICI')")
    @PostMapping("/al")
    public OduncAlmaSistemi kitapOduncAl(@RequestParam Integer kitapId, @RequestParam String planlananIadeTarihi,
            @AuthenticationPrincipal Kullanicilar kullanici) {
        return oduncService.kitapOduncAl(kitapId, kullanici, LocalDate.parse(planlananIadeTarihi));
    }

    // İADE
    @PreAuthorize("hasRole('KULLANICI')")
    @PutMapping("/iade/{oduncId}")
    public void kitapIade(@PathVariable Integer oduncId) {
        oduncService.kitapIade(oduncId);
    }

    // ÖDÜNÇLERİM
    @GetMapping("/aktif")
    @PreAuthorize("hasAnyRole('KULLANICI','ADMIN')")
    public List<OduncAlmaSistemi> aktifOduncler(@AuthenticationPrincipal Kullanicilar kullanici) {
        return oduncService.kullanicininAktifOduncleri(kullanici.getKullaniciId());
    }

}
