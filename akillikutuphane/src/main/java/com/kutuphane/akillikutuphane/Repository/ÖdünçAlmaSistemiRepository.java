package com.kutuphane.akillikutuphane.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kutuphane.akillikutuphane.Entity.ÖdünçAlmaSistemi;

@Repository
public interface ÖdünçAlmaSistemiRepository extends JpaRepository<ÖdünçAlmaSistemi, Integer> {
    
}
