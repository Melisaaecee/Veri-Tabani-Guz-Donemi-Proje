package com.kutuphane.akillikutuphane.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kutuphane.akillikutuphane.Entity.Kitap;

@Repository
public interface KitapRepository extends JpaRepository<Kitap, Integer> {
    @Query("""
                SELECT k FROM Kitap k
                JOIN k.kategori kat
                WHERE kat.kategoriAd = :kategoriAd
            """)
    List<Kitap> kategoriyeGoreKitaplar(@Param("kategoriAd") String kategoriAd);
}
