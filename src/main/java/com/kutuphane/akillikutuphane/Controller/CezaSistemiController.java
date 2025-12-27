package com.kutuphane.akillikutuphane.Controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.CezaSistemi;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Service.CezaService;

@RestController
@RequestMapping("/api/ceza")
public class CezaSistemiController {

    private final CezaService cezaService;

    public CezaSistemiController(CezaService cezaService) {
        this.cezaService = cezaService;
    }

    // KULLANICI – KENDİ CEZALARINI GÖR
    @GetMapping("/benim")
    public List<CezaSistemi> benimCezalarim(@AuthenticationPrincipal Kullanicilar kullanici) {
        return cezaService.kullaniciCezalari(kullanici);
    }

    // ADMİN - TÜM CEZALARI GÖRÜR
    @GetMapping("/admin/tum-cezalar")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CezaSistemi> tumCezalar() {
        return cezaService.tumCezalariGetir();
    }
}
