package com.kutuphane.akillikutuphane.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kutuphane.akillikutuphane.Entity.CezaSistemi;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Entity.OduncAlmaSistemi;

public interface CezaSistemiRepository extends JpaRepository<CezaSistemi, Integer> {
    Optional<CezaSistemi> findByOdunc(OduncAlmaSistemi odunc);

    List<CezaSistemi> findByOdunc_Kullanici(Kullanicilar kullanici);

}
