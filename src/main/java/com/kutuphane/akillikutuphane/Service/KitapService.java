package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Kategori;
import com.kutuphane.akillikutuphane.Entity.Kitap;
import com.kutuphane.akillikutuphane.Entity.Yazar;
import com.kutuphane.akillikutuphane.Repository.KategoriRepository;
import com.kutuphane.akillikutuphane.Repository.KitapRepository;
import com.kutuphane.akillikutuphane.Repository.YazarRepository;

@Service
public class KitapService {

    private final KitapRepository kitapRepository;
    private final KategoriRepository kategoriRepository;
    private final YazarRepository yazarRepository;

    public KitapService(KitapRepository kitapRepository,
            KategoriRepository kategoriRepository,
            YazarRepository yazarRepository) {
        this.kitapRepository = kitapRepository;
        this.kategoriRepository = kategoriRepository;
        this.yazarRepository = yazarRepository;
    }

    // TÜM KİTAPLAR
    public List<Kitap> tumunuGetir() {
        return kitapRepository.findAll();
    }

    // ID'YE GÖRE KİTAP
    public Kitap idyeGoreBul(Integer id) {
        return kitapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
    }

    public Kitap kaydet(Kitap kitap, Integer kategoriId, Integer yazarId) {
        Kategori kategori = kategoriRepository.findById(kategoriId)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        Yazar yazar = yazarRepository.findById(yazarId)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı"));

        kitap.setKategori(kategori);
        kitap.setYazar(yazar);
        kitap.setDurum(true);

        return kitapRepository.save(kitap);
    }

    // Güncelleme
    public Kitap guncelle(Integer id, Kitap kitapDetay, Integer yazarId, Integer kategoriId) {
        Kitap kitap = kitapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        Yazar yazar = yazarRepository.findById(yazarId)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı"));

        Kategori kategori = kategoriRepository.findById(kategoriId)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        kitap.setKitapAd(kitapDetay.getKitapAd());
        kitap.setStokAdedi(kitapDetay.getStokAdedi());
        kitap.setDurum(kitapDetay.getDurum());
        kitap.setYazar(yazar);
        kitap.setKategori(kategori);

        return kitapRepository.save(kitap);
    }

    // KİTAP SİL
    public void sil(Integer id) {
        Kitap kitap = kitapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        kitapRepository.delete(kitap);
    }

    // KATEGORİYE GÖRE KİTAPLAR
    public List<Kitap> kategoriyeGoreGetir(String kategoriAd) {
        return kitapRepository.kategoriyeGoreKitaplar(kategoriAd);
    }

}
