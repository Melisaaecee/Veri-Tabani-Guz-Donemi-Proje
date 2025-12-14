package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.CezaSistemi;
import com.kutuphane.akillikutuphane.Service.CezaSistemiService;

@RestController
@RequestMapping("/api/ceza")
public class CezaSistemiController {
    @Autowired
    private CezaSistemiService cezaSistemiService;

    @GetMapping
    public Iterable<CezaSistemi> getAll() {
        return cezaSistemiService.tumunuGetir();
    }

    @GetMapping("/{id}")
    public CezaSistemi getById(@PathVariable Integer id) {
        return cezaSistemiService.idyeGoreBul(id);
    }

    @PostMapping
    public CezaSistemi create(@RequestBody CezaSistemi ceza) {
        return cezaSistemiService.kaydet(ceza);
    }

    @PutMapping("/{id}")
    public CezaSistemi update(@PathVariable Integer id, @RequestBody CezaSistemi cezaDetails) {
        return cezaSistemiService.guncelle(id, cezaDetails);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        cezaSistemiService.sil(id);
    }
}
