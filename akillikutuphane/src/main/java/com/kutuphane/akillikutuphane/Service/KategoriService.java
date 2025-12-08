package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Kategori;
import com.kutuphane.akillikutuphane.Repository.KategoriRepository;

@Service
public class KategoriService {
    @Autowired
    private KategoriRepository kategoriRepository;

    public List<Kategori> getAll() {
        return kategoriRepository.findAll();
    }

    public Kategori getById(Integer id) {
        return kategoriRepository.findById(id).orElse(null);
    }

    public Kategori save(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }

    public Kategori update(Integer id, Kategori kategoriDetails) {
        return kategoriRepository.findById(id).map(kategori -> {
            kategori.setKategori_ad(kategoriDetails.getKategori_ad());
            return kategoriRepository.save(kategori);
        }).orElse(null);
    }

    public void delete(Integer id) {
        kategoriRepository.deleteById(id);
    }
}
