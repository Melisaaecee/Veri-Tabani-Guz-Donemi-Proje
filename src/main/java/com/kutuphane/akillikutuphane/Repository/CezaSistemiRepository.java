package com.kutuphane.akillikutuphane.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kutuphane.akillikutuphane.Entity.CezaSistemi;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;

public interface CezaSistemiRepository extends JpaRepository<CezaSistemi, Integer> {
   
    List<CezaSistemi> findByOdunc_Kullanici(Kullanicilar kullanici);

    Optional<CezaSistemi> findByOduncId(Integer oduncId);

}
