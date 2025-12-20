package com.kutuphane.akillikutuphane.Service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Kitap;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Entity.OduncAlmaSistemi;
import com.kutuphane.akillikutuphane.Repository.KitapRepository;
import com.kutuphane.akillikutuphane.Repository.OduncAlmaSistemiRepository;
import jakarta.transaction.Transactional;

@Service
public class OduncAlmaSistemiService {

    private final OduncAlmaSistemiRepository oduncRepository;
    private final KitapRepository kitapRepository;

    public OduncAlmaSistemiService(OduncAlmaSistemiRepository oduncRepository, KitapRepository kitapRepository) {
        this.oduncRepository = oduncRepository;
        this.kitapRepository = kitapRepository;
    }

    // KİTAP ÖDÜNÇ AL
    @Transactional
    public OduncAlmaSistemi kitapOduncAl(Integer kitapId, Kullanicilar kullanici, LocalDate planlananIade) {

        Kitap kitap = kitapRepository.findById(kitapId)
                .orElseThrow(() -> new RuntimeException("Kitap yok"));

        if (kitap.getStokAdedi() <= 0)
            throw new RuntimeException("Stokta kitap yok");

        kitap.setStokAdedi(kitap.getStokAdedi() - 1);

        OduncAlmaSistemi odunc = new OduncAlmaSistemi();
        odunc.setKitap(kitap);
        odunc.setKullanici(kullanici);
        odunc.setAlinanTarih(LocalDate.now());
        odunc.setPlanlananIadeTarihi(planlananIade);
        odunc.setDurum(true);

        return oduncRepository.save(odunc);
    }

    // KİTAP İADE
    public void kitapIade(Integer oduncId) {

        OduncAlmaSistemi odunc = oduncRepository.findById(oduncId)
                .orElseThrow(() -> new RuntimeException("Ödünç bulunamadı"));

        Kitap kitap = odunc.getKitap();
        kitap.setStokAdedi(kitap.getStokAdedi() + 1);
        odunc.setGercekIadeTarihi(LocalDate.now());
        odunc.setDurum(false);

        oduncRepository.save(odunc);
    }

    public List<OduncAlmaSistemi> kullanicininAktifOduncleri(int kullaniciId) {
        return oduncRepository.aktifOduncler(kullaniciId);
    }
}
