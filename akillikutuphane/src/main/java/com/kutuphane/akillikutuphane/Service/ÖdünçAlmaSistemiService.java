package com.kutuphane.akillikutuphane.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.kutuphane.akillikutuphane.Entity.Kitap;
import com.kutuphane.akillikutuphane.Entity.Kullanıcılar;
import com.kutuphane.akillikutuphane.Entity.ÖdünçAlmaSistemi;
import com.kutuphane.akillikutuphane.Repository.KitapRepository;
import com.kutuphane.akillikutuphane.Repository.KullanıcılarRepository;
import com.kutuphane.akillikutuphane.Repository.ÖdünçAlmaSistemiRepository;

@Service
public class ÖdünçAlmaSistemiService {

    private final ÖdünçAlmaSistemiRepository ödünçAlmaRepository;
    private final KitapRepository kitapRepository;
    private final KullanıcılarRepository kullanıcıRepository;

    public ÖdünçAlmaSistemiService(
            ÖdünçAlmaSistemiRepository ödünçAlmaRepository,
            KitapRepository kitapRepository,
            KullanıcılarRepository kullanıcıRepository) {

        this.ödünçAlmaRepository = ödünçAlmaRepository;
        this.kitapRepository = kitapRepository;
        this.kullanıcıRepository = kullanıcıRepository;
    }

    // 📌 ÖDÜNÇ ALMA
    public ÖdünçAlmaSistemi ödünçAl(Integer kitapId, Integer kullanıcıId) {

        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        Kullanıcılar kullanıcı = kullanıcıRepository.findById(kullanıcıId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        ÖdünçAlmaSistemi ödünç = new ÖdünçAlmaSistemi();
        ödünç.setAlınan_tarih(new java.util.Date());
        ödünç.setPlanlanan_iade_tarihi(new java.util.Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000));
        ödünç.setDurum(true);

        return ödünçAlmaRepository.save(ödünç);
    }

    // 📌 İADE
    public ÖdünçAlmaSistemi iadeEt(Integer ödünçId) {

        ÖdünçAlmaSistemi ödünç = ödünçAlmaRepository.findById(ödünçId)
                .orElseThrow(() -> new RuntimeException("Ödünç kaydı bulunamadı"));

        ödünç.setGerçek_iade_tarihi(new java.util.Date());
        ödünç.setDurum(false);

        return ödünçAlmaRepository.save(ödünç);
    }
}
