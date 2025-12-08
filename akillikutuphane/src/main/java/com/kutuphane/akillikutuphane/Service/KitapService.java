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

    public List<Kitap> getAll() {
        return kitapRepository.findAll();
    }

    public Kitap getById(Integer id) {
        return kitapRepository.findById(id).orElse(null);
    }

    public Kitap save(Kitap kitap) {
        return kitapRepository.save(kitap);
    }

    public Kitap update(Integer id, Kitap kitapDetails) {
        return kitapRepository.findById(id).map(kitap -> {
            kitap.setKitap_ad(kitapDetails.getKitap_ad());
            kitap.setDurum(kitapDetails.isDurum());
            kitap.setStok_adedi(kitapDetails.getStok_adedi());
            return kitapRepository.save(kitap);
        }).orElse(null);
    }

    public void delete(Integer id) {
        kitapRepository.deleteById(id);
    }
}
