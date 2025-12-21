package com.kutuphane.akillikutuphane.Service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Dto.RegisterRequest;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Entity.Role;
import com.kutuphane.akillikutuphane.Repository.KullanicilarRepository;

@Service
public class KullanicilarService {

    private final KullanicilarRepository repository;
    private final PasswordEncoder passwordEncoder;

    public KullanicilarService(KullanicilarRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // TÜM KULLANICILAR (ADMIN)
    public List<Kullanicilar> tumunuGetir() {
        return repository.findAll();
    }

    // ID İLE
    public Kullanicilar idIleGetir(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }

    // Email ile
    public Kullanicilar emailIleGetir(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }

    // REGISTER
    public Kullanicilar register(RegisterRequest dto) {

        // Eğer email zaten varsa hata fırlat
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email zaten kayıtlı");
        }

        Kullanicilar kullanici = new Kullanicilar();
        kullanici.setKullaniciAd(dto.getKullaniciAd());
        kullanici.setKullaniciSoyad(dto.getKullaniciSoyad());
        kullanici.setEmail(dto.getEmail());
        kullanici.setSifre(passwordEncoder.encode(dto.getSifre())); // şifre hash'leniyor
        kullanici.setRol(Role.KULLANICI); // varsayılan rol

        return repository.save(kullanici);
    }

    // LOGIN
    public Kullanicilar login(String email, String sifre) {

        Kullanicilar kullanici = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (!passwordEncoder.matches(sifre, kullanici.getSifre())) {
            throw new RuntimeException("Şifre hatalı");
        }

        return kullanici;
    }

    // SİL (ADMIN)
    public void sil(int id) {
        repository.deleteById(id);
    }

}
