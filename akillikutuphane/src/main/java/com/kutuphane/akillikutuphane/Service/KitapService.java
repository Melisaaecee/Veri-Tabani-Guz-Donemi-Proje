package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Kitap;
import com.kutuphane.akillikutuphane.Repository.KitapRepository;

@Service
public class KitapService {
    @Autowired
    private KitapRepository kitapRepository;

    public List<Kitap> tumunuGetir() {
        return kitapRepository.findAll();
    }

    public Kitap idyeGoreBul(Integer id) {
        return kitapRepository.findById(id).orElse(null);
    }

    public Kitap kaydet(Kitap kitap) {
        return kitapRepository.save(kitap);
    }

    public Kitap guncelle(Integer id, Kitap kitapDetails) {
        return kitapRepository.findById(id).map(kitap -> {
            kitap.setKitap_ad(kitapDetails.getKitap_ad());
            kitap.setDurum(kitapDetails.isDurum());
            kitap.setStok_adedi(kitapDetails.getStok_adedi());
            return kitapRepository.save(kitap);
        }).orElse(null);
    }

    public void sil(Integer id) {
        kitapRepository.deleteById(id);
    }

    public void stokAzalt(Integer kitapId) {
        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        
        if (kitap.getStok_adedi() <= 0) {
            throw new RuntimeException("Stokta kitap yok");
        }
        
        kitap.setStok_adedi(kitap.getStok_adedi() - 1);
        kitapRepository.save(kitap);
    }

    public void stokArtir(Integer kitapId) {
        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        
        kitap.setStok_adedi(kitap.getStok_adedi() + 1);
        kitapRepository.save(kitap);
    }
}
