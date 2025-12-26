package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.CezaSistemi;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Repository.CezaSistemiRepository;

@Service
public class CezaService {

    private final CezaSistemiRepository cezaRepository;

    public CezaService(CezaSistemiRepository cezaRepository) {
        this.cezaRepository = cezaRepository;

    }

    public List<CezaSistemi> kullaniciCezalari(Kullanicilar kullanici) {
        return cezaRepository.findByOdunc_Kullanici(kullanici);
    }

    public List<CezaSistemi> tumCezalariGetir() {
        return cezaRepository.findAll();
    }
}
