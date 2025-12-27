package com.kutuphane.akillikutuphane.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, String>> kitapIade(@PathVariable Integer oduncId) {
        oduncService.kitapIade(oduncId);
        
        // Geriye boş bir void yerine JSON formatında bir cevap dönüyoruz
        Map<String, String> response = new HashMap<>();
        response.put("mesaj", "İade işlemi başarılı");
        return ResponseEntity.ok(response);
    }

    // ÖDÜNÇLERİM
    @GetMapping("/aktif")
    @PreAuthorize("hasAnyRole('KULLANICI')")
    public List<OduncAlmaSistemi> aktifOduncler(@AuthenticationPrincipal Kullanicilar kullanici) {
        return oduncService.kullanicininAktifOduncleri(kullanici.getKullaniciId());
    }

    @GetMapping("/gecmis")
    @PreAuthorize("hasAnyRole('KULLANICI')")
    public List<OduncAlmaSistemi> gecmisOduncler(@AuthenticationPrincipal Kullanicilar kullanici) {
        return oduncService.kullanicininİadeleri(kullanici.getKullaniciId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/tum-oduncler")
    public List<OduncAlmaSistemi> tumOduncler() {
        return oduncService.tumOduncler();
    }

}
