package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Kategori;
import com.kutuphane.akillikutuphane.Repository.KategoriRepository;

@Service
public class KategoriService {

    private final KategoriRepository kategoriRepository;

    public KategoriService(KategoriRepository kategoriRepository) {
        this.kategoriRepository = kategoriRepository;
    }

    public List<Kategori> tumunuGetir() {
        return kategoriRepository.findAll();
    }

    public Kategori kaydet(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }

 
    public Kategori idIleGetir(int id) {
        return kategoriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı: " + id));
    }

    
    public Kategori guncelle(int id, Kategori yeniKategori) {
        Kategori mevcut = idIleGetir(id); // önce var mı kontrol et
        mevcut.setKategoriAd(yeniKategori.getKategoriAd());
        return kategoriRepository.save(mevcut);
    }

    // Delete
    public void sil(int id) {
        Kategori mevcut = idIleGetir(id); // önce var mı kontrol et
        kategoriRepository.delete(mevcut);
    }

}
