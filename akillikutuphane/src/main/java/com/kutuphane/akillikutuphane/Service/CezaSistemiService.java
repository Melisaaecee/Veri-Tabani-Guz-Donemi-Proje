package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.CezaSistemi;
import com.kutuphane.akillikutuphane.Repository.CezaSistemiRepository;

@Service
public class CezaSistemiService {
    @Autowired
    private CezaSistemiRepository cezaSistemiRepository;

    public List<CezaSistemi> tumunuGetir() {
        return cezaSistemiRepository.findAll();
    }

    public CezaSistemi idyeGoreBul(Integer id) {
        return cezaSistemiRepository.findById(id).orElse(null);
    }

    public CezaSistemi kaydet(CezaSistemi ceza) {
        if (ceza == null) {
            throw new IllegalArgumentException("Ceza sistemi null olamaz");
        }
        if (ceza.getCeza_miktarı() == null || ceza.getCeza_miktarı() <= 0) {
            throw new IllegalArgumentException("Ceza miktarı 0'dan büyük olmalıdır");
        }
        return cezaSistemiRepository.save(ceza);
    }

    public CezaSistemi guncelle(Integer id, CezaSistemi cezaDetails) {
        return cezaSistemiRepository.findById(id).map(ceza -> {
            ceza.setCeza_miktarı(cezaDetails.getCeza_miktarı());
            return cezaSistemiRepository.save(ceza);
        }).orElse(null);
    }

    public void sil(Integer id) {
        cezaSistemiRepository.deleteById(id);
    }
}
