package com.kutuphane.akillikutuphane.Controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.kutuphane.akillikutuphane.Config.JwtUtil;
import com.kutuphane.akillikutuphane.Dto.LoginRequest;
import com.kutuphane.akillikutuphane.Dto.RegisterRequest;
import com.kutuphane.akillikutuphane.Entity.Kullanicilar;
import com.kutuphane.akillikutuphane.Service.KullanicilarService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final KullanicilarService kullaniciService;

    public AuthController(AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            KullanicilarService kullaniciService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.kullaniciService = kullaniciService;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSifre()));

        String email = authentication.getName();
        String rol = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority(); // ROLE_KULLANICI veya ROLE_ADMIN

        String token = jwtUtil.generateToken(email, rol);

        return ResponseEntity.ok(Map.of("token", token));
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest dto) {

        // Kullanıcıyı servis ile oluştur
        Kullanicilar kullanici = kullaniciService.register(dto);

        // JSON yanıt
        return ResponseEntity.ok(Map.of(
                "message", "Kayıt başarılı",
                "email", kullanici.getEmail()));
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication auth) {

        return Map.of(
                "email", auth.getName(),
                "rol", auth.getAuthorities().iterator().next().getAuthority());
    }
}
