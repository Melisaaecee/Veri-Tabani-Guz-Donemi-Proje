package com.kutuphane.akillikutuphane.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Repository.KullanicilarRepository;

@Service
public class KullanicilarDetailsService implements UserDetailsService {

    private final KullanicilarRepository repository;

    public KullanicilarDetailsService(KullanicilarRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Kullanicilar kullanici = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));

        return kullanici;
    }

}
