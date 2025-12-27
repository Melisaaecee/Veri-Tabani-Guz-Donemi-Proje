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

  @Query("""
          SELECT o FROM OduncAlmaSistemi o
          WHERE o.kullanici.kullaniciId = :kullaniciId
            AND o.durum = false
      """)
  List<OduncAlmaSistemi> iadeEdilenler(@Param("kullaniciId") int kullaniciId);

  List<OduncAlmaSistemi> findByGercekIadeTarihiIsNull();

  List<OduncAlmaSistemi> findByGercekIadeTarihiIsNotNull();

  @Query("SELECT o.kullanici.email, o.kitap.kitapAd FROM OduncAlmaSistemi o WHERE o.oduncId = :id")
      Object findRawMailData(@Param("id") Integer id);

}
