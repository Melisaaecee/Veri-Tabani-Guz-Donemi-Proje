package com.kutuphane.akillikutuphane.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kutuphane.akillikutuphane.Entity.Kullanıcılar;

@Repository
public interface KullanıcılarRepository extends JpaRepository<Kullanıcılar, Integer> {
    
}
