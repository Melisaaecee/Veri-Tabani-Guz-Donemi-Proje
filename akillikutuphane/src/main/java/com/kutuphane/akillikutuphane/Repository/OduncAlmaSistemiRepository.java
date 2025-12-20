package com.kutuphane.akillikutuphane.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kutuphane.akillikutuphane.Entity.OduncAlmaSistemi;

@Repository
public interface OduncAlmaSistemiRepository extends JpaRepository<OduncAlmaSistemi, Integer> {
    @Query("""
                SELECT o FROM OduncAlmaSistemi o
                WHERE o.kullanici.kullaniciId = :kullaniciId
                  AND o.durum = true
            """)
    List<OduncAlmaSistemi> aktifOduncler(@Param("kullaniciId") int kullaniciId);
}
