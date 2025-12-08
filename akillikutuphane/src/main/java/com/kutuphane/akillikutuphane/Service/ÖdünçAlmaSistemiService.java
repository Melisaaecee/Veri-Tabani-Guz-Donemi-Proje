package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.ÖdünçAlmaSistemi;
import com.kutuphane.akillikutuphane.Repository.ÖdünçAlmaSistemiRepository;

@Service
public class ÖdünçAlmaSistemiService {
    @Autowired
    private ÖdünçAlmaSistemiRepository ödünçAlmaSistemiRepository;

    public List<ÖdünçAlmaSistemi> getAll() {
        return ödünçAlmaSistemiRepository.findAll();
    }

    public ÖdünçAlmaSistemi getById(Integer id) {
        return ödünçAlmaSistemiRepository.findById(id).orElse(null);
    }

    public ÖdünçAlmaSistemi save(ÖdünçAlmaSistemi ödünçAlma) {
        return ödünçAlmaSistemiRepository.save(ödünçAlma);
    }

    public ÖdünçAlmaSistemi update(Integer id, ÖdünçAlmaSistemi ödünçAlmaDetails) {
        return ödünçAlmaSistemiRepository.findById(id).map(ödünçAlma -> {
            ödünçAlma.setAlınan_tarih(ödünçAlmaDetails.getAlınan_tarih());
            ödünçAlma.setDurum(ödünçAlmaDetails.isDurum());
            ödünçAlma.setGerçek_iade_tarihi(ödünçAlmaDetails.getGerçek_iade_tarihi());
            ödünçAlma.setPlanlanan_iade_tarihi(ödünçAlmaDetails.getPlanlanan_iade_tarihi());
            return ödünçAlmaSistemiRepository.save(ödünçAlma);
        }).orElse(null);
    }

    public void delete(Integer id) {
        ödünçAlmaSistemiRepository.deleteById(id);
    }
}
