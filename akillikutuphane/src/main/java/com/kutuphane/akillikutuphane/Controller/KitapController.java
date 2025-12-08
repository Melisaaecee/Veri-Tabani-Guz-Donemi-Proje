package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Kitap;
import com.kutuphane.akillikutuphane.Repository.KitapRepository;

@RestController
@RequestMapping("/api/kitap")
public class KitapController {
    @Autowired
    private KitapRepository kitapRepository;

    @GetMapping
    public Iterable<Kitap> getAll() {
        return kitapRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kitap getById(@PathVariable Integer id) {
        return kitapRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Kitap create(@RequestBody Kitap kitap) {
        return kitapRepository.save(kitap);
    }

    @PutMapping("/{id}")
    public Kitap update(@PathVariable Integer id, @RequestBody Kitap kitapDetails) {
        return kitapRepository.findById(id).map(kitap -> {
            kitap.setKitap_ad(kitapDetails.getKitap_ad());
            kitap.setDurum(kitapDetails.isDurum());
            kitap.setStok_adedi(kitapDetails.getStok_adedi());
            return kitapRepository.save(kitap);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kitapRepository.deleteById(id);
    }
}
