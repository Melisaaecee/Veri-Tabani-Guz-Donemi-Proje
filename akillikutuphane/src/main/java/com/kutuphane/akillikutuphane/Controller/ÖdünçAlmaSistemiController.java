package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.ÖdünçAlmaSistemi;
import com.kutuphane.akillikutuphane.Repository.ÖdünçAlmaSistemiRepository;

@RestController
@RequestMapping("/api/oduncalma")
public class ÖdünçAlmaSistemiController {
    @Autowired
    private ÖdünçAlmaSistemiRepository ödünçAlmaSistemiRepository;

    @GetMapping
    public Iterable<ÖdünçAlmaSistemi> getAll() {
        return ödünçAlmaSistemiRepository.findAll();
    }

    @GetMapping("/{id}")
    public ÖdünçAlmaSistemi getById(@PathVariable Integer id) {
        return ödünçAlmaSistemiRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ÖdünçAlmaSistemi create(@RequestBody ÖdünçAlmaSistemi ödünçAlma) {
        return ödünçAlmaSistemiRepository.save(ödünçAlma);
    }

    @PutMapping("/{id}")
    public ÖdünçAlmaSistemi update(@PathVariable Integer id, @RequestBody ÖdünçAlmaSistemi ödünçAlmaDetails) {
        return ödünçAlmaSistemiRepository.findById(id).map(ödünçAlma -> {
            ödünçAlma.setAlınan_tarih(ödünçAlmaDetails.getAlınan_tarih());
            ödünçAlma.setDurum(ödünçAlmaDetails.isDurum());
            ödünçAlma.setGerçek_iade_tarihi(ödünçAlmaDetails.getGerçek_iade_tarihi());
            ödünçAlma.setPlanlanan_iade_tarihi(ödünçAlmaDetails.getPlanlanan_iade_tarihi());
            return ödünçAlmaSistemiRepository.save(ödünçAlma);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ödünçAlmaSistemiRepository.deleteById(id);
    }
}
