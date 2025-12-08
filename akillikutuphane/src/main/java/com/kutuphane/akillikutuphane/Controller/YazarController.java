package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Yazar;
import com.kutuphane.akillikutuphane.Repository.YazarRepository;

@RestController
@RequestMapping("/api/yazar")
public class YazarController {
    @Autowired
    private YazarRepository yazarRepository;

    @GetMapping
    public Iterable<Yazar> getAll() {
        return yazarRepository.findAll();
    }

    @GetMapping("/{id}")
    public Yazar getById(@PathVariable Integer id) {
        return yazarRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Yazar create(@RequestBody Yazar yazar) {
        return yazarRepository.save(yazar);
    }

    @PutMapping("/{id}")
    public Yazar update(@PathVariable Integer id, @RequestBody Yazar yazarDetails) {
        return yazarRepository.findById(id).map(yazar -> {
            yazar.setYazar_ad(yazarDetails.getYazar_ad());
            yazar.setYazar_soyad(yazarDetails.getYazar_soyad());
            return yazarRepository.save(yazar);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        yazarRepository.deleteById(id);
    }
}
