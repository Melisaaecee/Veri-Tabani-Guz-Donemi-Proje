package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Yazar;
import com.kutuphane.akillikutuphane.Repository.YazarRepository;

@Service
public class YazarService {
    @Autowired
    private YazarRepository yazarRepository;

    public List<Yazar> tumunuGetir() {
        return yazarRepository.findAll();
    }

    public Yazar idyeGoreBul(Integer id) {
        return yazarRepository.findById(id).orElse(null);
    }

    public Yazar kaydet(Yazar yazar) {
        if (yazar == null || yazar.getYazar_ad() == null || yazar.getYazar_ad().isEmpty()) {
            throw new IllegalArgumentException("Yazar adı boş olamaz");
        }
        return yazarRepository.save(yazar);
    }

    public Yazar guncelle(Integer id, Yazar yazarDetails) {
        return yazarRepository.findById(id).map(yazar -> {
            yazar.setYazar_ad(yazarDetails.getYazar_ad());
            yazar.setYazar_soyad(yazarDetails.getYazar_soyad());
            return yazarRepository.save(yazar);
        }).orElse(null);
    }

    public void sil(Integer id) {
        yazarRepository.deleteById(id);
    }
}
