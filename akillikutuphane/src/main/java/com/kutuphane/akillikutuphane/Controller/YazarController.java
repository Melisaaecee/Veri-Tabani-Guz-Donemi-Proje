package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.Yazar;
import com.kutuphane.akillikutuphane.Service.YazarService;

@RestController
@RequestMapping("/api/yazar")
public class YazarController {
    @Autowired
    private YazarService yazarService;

    @GetMapping
    public Iterable<Yazar> getAll() {
        return yazarService.tumunuGetir();
    }

    @GetMapping("/{id}")
    public Yazar getById(@PathVariable Integer id) {
        return yazarService.idyeGoreBul(id);
    }

    @PostMapping
    public Yazar create(@RequestBody Yazar yazar) {
        return yazarService.kaydet(yazar);
    }

    @PutMapping("/{id}")
    public Yazar update(@PathVariable Integer id, @RequestBody Yazar yazarDetails) {
        return yazarService.guncelle(id, yazarDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        yazarService.sil(id);
    }
}
