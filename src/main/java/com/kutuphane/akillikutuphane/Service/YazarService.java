package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Yazar;
import com.kutuphane.akillikutuphane.Repository.YazarRepository;

@Service
public class YazarService {

    private final YazarRepository yazarRepository;

    public YazarService(YazarRepository yazarRepository) {
        this.yazarRepository = yazarRepository;
    }

    public List<Yazar> tumunuGetir() {
        return yazarRepository.findAll();
    }

    public Yazar idyeGoreBul(Integer id) {
        return yazarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı"));
    }

    public Yazar kaydet(Yazar yazar) {
        return yazarRepository.save(yazar);
    }

    public Yazar guncelle(Integer id, Yazar yazarDetay) {

        Yazar yazar = yazarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı"));

        yazar.setYazar_ad(yazarDetay.getYazar_ad());
        yazar.setYazar_soyad(yazarDetay.getYazar_soyad());

        return yazarRepository.save(yazar);
    }

    public void sil(Integer id) {
        Yazar yazar = yazarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı"));
        yazarRepository.delete(yazar);
    }
}
