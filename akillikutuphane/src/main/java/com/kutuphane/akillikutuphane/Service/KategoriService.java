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

    public List<Kategori> tumunuGetir() {
        return kategoriRepository.findAll();
    }

    public Kategori idyeGoreBul(Integer id) {
        return kategoriRepository.findById(id).orElse(null);
    }

    public Kategori kaydet(Kategori kategori) {
        if (kategori == null || kategori.getKategori_ad() == null || kategori.getKategori_ad().isEmpty()) {
            throw new IllegalArgumentException("Kategori adı boş olamaz");
        }
        return kategoriRepository.save(kategori);
    }

    public Kategori guncelle(Integer id, Kategori kategoriDetails) {
        return kategoriRepository.findById(id).map(kategori -> {
            kategori.setKategori_ad(kategoriDetails.getKategori_ad());
            return kategoriRepository.save(kategori);
        }).orElse(null);
    }

    public void sil(Integer id) {
        kategoriRepository.deleteById(id);
    }
}
