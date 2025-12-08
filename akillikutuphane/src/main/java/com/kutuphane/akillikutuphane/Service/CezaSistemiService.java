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

    public List<CezaSistemi> getAll() {
        return cezaSistemiRepository.findAll();
    }

    public CezaSistemi getById(Integer id) {
        return cezaSistemiRepository.findById(id).orElse(null);
    }

    public CezaSistemi save(CezaSistemi ceza) {
        return cezaSistemiRepository.save(ceza);
    }

    public CezaSistemi update(Integer id, CezaSistemi cezaDetails) {
        return cezaSistemiRepository.findById(id).map(ceza -> {
            ceza.setCeza_miktarı(cezaDetails.getCeza_miktarı());
            return cezaSistemiRepository.save(ceza);
        }).orElse(null);
    }

    public void delete(Integer id) {
        cezaSistemiRepository.deleteById(id);
    }
}
