package com.kutuphane.akillikutuphane.Config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Entity.Role;
import com.kutuphane.akillikutuphane.Repository.KullanicilarRepository;

@Component
public class AdminInitializer {

    private final KullanicilarRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(KullanicilarRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initAdmin() {

        String adminEmail = "adminkutuphane@gmail.com";

        //eğer admin varsa ekleme
        if (repository.findByEmail(adminEmail).isPresent()) {
            System.out.println("Admin zaten mevcut, tekrar eklenmedi.");
            return;
        }

        Kullanicilar admin = new Kullanicilar();
        admin.setEmail(adminEmail);
        admin.setKullaniciAd("Admin");
        admin.setKullaniciSoyad("System");
        admin.setRol(Role.ADMIN);
        admin.setSifre(passwordEncoder.encode("admin123"));

        repository.save(admin);

        System.out.println("Admin başarıyla oluşturuldu.");
    }
}
