package com.kutuphane.akillikutuphane.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Entity.CezaSistemi;
import com.kutuphane.akillikutuphane.Repository.CezaSistemiRepository;

@RestController
@RequestMapping("/api/ceza")
public class CezaSistemiController {
    @Autowired
    private CezaSistemiRepository cezaSistemiRepository;

    @GetMapping
    public Iterable<CezaSistemi> getAll() {
        return cezaSistemiRepository.findAll();
    }

    @GetMapping("/{id}")
    public CezaSistemi getById(@PathVariable Integer id) {
        return cezaSistemiRepository.findById(id).orElse(null);
    }

    @PostMapping
    public CezaSistemi create(@RequestBody CezaSistemi ceza) {
        return cezaSistemiRepository.save(ceza);
    }

    @PutMapping("/{id}")
    public CezaSistemi update(@PathVariable Integer id, @RequestBody CezaSistemi cezaDetails) {
        return cezaSistemiRepository.findById(id).map(ceza -> {
            ceza.setCeza_miktarı(cezaDetails.getCeza_miktarı());
            return cezaSistemiRepository.save(ceza);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        cezaSistemiRepository.deleteById(id);
    }
}
