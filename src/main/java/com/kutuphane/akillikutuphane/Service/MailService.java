package com.kutuphane.akillikutuphane.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void iadeMailiGonder(String aliciEmail, String kitapAd, int ceza) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(aliciEmail);
        message.setSubject("Kitap İade Onayı - Akıllı Kütüphane");

        String icerik = "Sayın üyemiz,\n\n" +
                "'" + kitapAd + "' isimli kitabı başarıyla iade ettiniz.\n";

        if (ceza > 0) {
            icerik += "Geç iade nedeniyle oluşan ceza miktarınız: " + ceza + " TL'dir.\n";
        } else {
            icerik += "Kitabı zamanında iade ettiğiniz için teşekkür ederiz.\n";
        }

        icerik += "\nİyi okumalar dileriz!";

        message.setText(icerik);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Mail gönderilirken hata oluştu: " + e.getMessage());
        }
    }
}
