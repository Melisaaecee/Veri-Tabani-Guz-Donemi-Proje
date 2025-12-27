package com.kutuphane.akillikutuphane.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kutuphane.akillikutuphane.Entity.Kategori;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Integer> {

}
