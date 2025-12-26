package com.kutuphane.akillikutuphane.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kutuphane.akillikutuphane.Entity.Kullanicilar;

@Repository
public interface KullanicilarRepository extends JpaRepository<Kullanicilar, Integer> {
    Optional<Kullanicilar> findByEmail(String email);
}
