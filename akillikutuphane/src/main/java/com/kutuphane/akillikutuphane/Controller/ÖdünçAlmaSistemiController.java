package com.kutuphane.akillikutuphane.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kutuphane.akillikutuphane.Entity.ÖdünçAlmaSistemi;
import com.kutuphane.akillikutuphane.Service.ÖdünçAlmaSistemiService;

@RestController
@RequestMapping("/api/odunc")
public class ÖdünçAlmaSistemiController {

    private final ÖdünçAlmaSistemiService ödünçService;

    public ÖdünçAlmaSistemiController(ÖdünçAlmaSistemiService ödünçService) {
        this.ödünçService = ödünçService;
    }

    // 📌 KİTAP ÖDÜNÇ AL
    @PostMapping("/al")
    public ResponseEntity<ÖdünçAlmaSistemi> oduncAl(
            @RequestParam Integer kitapId,
            @RequestParam Integer kullaniciId) {

        ÖdünçAlmaSistemi sonuc = ödünçService.ödünçAl(kitapId, kullaniciId);
        return ResponseEntity.ok(sonuc);
    }

    // 📌 KİTAP İADE ET
    @PutMapping("/iade/{oduncId}")
    public ResponseEntity<ÖdünçAlmaSistemi> iadeEt(
            @PathVariable Integer oduncId) {

        ÖdünçAlmaSistemi sonuc = ödünçService.iadeEt(oduncId);
        return ResponseEntity.ok(sonuc);
    }
}
