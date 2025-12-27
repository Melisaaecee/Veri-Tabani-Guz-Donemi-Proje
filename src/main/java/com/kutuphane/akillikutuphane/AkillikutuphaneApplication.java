package com.kutuphane.akillikutuphane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AkillikutuphaneApplication {

    public static void main(String[] args) {
    
        System.out.println("Uygulama başlatılıyor...");

        SpringApplication.run(AkillikutuphaneApplication.class, args);

        System.out.println("Uygulama çalışıyor.");
    }
}
