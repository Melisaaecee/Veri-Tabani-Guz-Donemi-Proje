# Veri-Tabani-Guz-Donemi-Proje

Akıllı Kütüphane Yönetim Sistemi
Bu proje, modern yazılım geliştirme prensiplerine (Katmanlı Mimari) uygun olarak geliştirilmiş, uçtan uca bir Kütüphane Yönetim Sistemi'dir. İlişkisel veritabanı yönetiminden JWT tabanlı güvenliğe kadar geniş bir teknik yelpazeyi kapsar.

Projenin Amacı 
Projenin temel hedefi; kitap takibi, ödünç alma, iade ve ceza süreçlerini otomatize ederek kütüphane yönetimini dijitalleştirmektir.

Teknik Kazanımlar
   -İlişkisel Veritabanı: 3. Normal Form (3NF) kurallarına uygun tasarım.
   -Trigger Kullanımı: Veritabanı seviyesinde otomatik ceza hesaplama ve stok yönetimi.
   -Katmanlı Mimari: Sürdürülebilir ve test edilebilir kod yapısı.
   -Güvenlik: JWT ile rol bazlı (Admin/Kullanıcı) yetkilendirme.
   -Harici Entegrasyon: Geciken iadeler için otomatik e-posta bildirim sistemi. 

Teknik Mimari
Proje, backend tarafında "Katmanlı Mimari" prensibiyle inşa edilmiştir:

   ->Entity Katmanı: Veritabanı tablolarının Java modelleri.
   ->Repository Katmanı: Veri erişim (CRUD) operasyonları.
   ->Service Katmanı: İş mantığının yürütüldüğü yer.
   ->Controller Katmanı: REST API uç noktalarının (Endpoints) yönetimi. 

Veritabanı Tasarımı (3NF)
Sistemdeki veriler tutarlılık ve hız odaklı modellenmiştir.

Varlık İlişki Diyagramı (ER)
   *Kitaplar & Yazarlar/Kategoriler: Many-to-One ilişkisi.
   *Ödünç İşlemleri: Kullanıcılar ve Kitaplar arasındaki köprü tablo.
   *Cezalar: Geciken işlemler için otomatik tetiklenen kayıtlar.

Tetikleyiciler (Triggers)Veritabanı düzeyinde aşağıdaki işlemler otomatik olarak gerçekleşir:

   ->Stok Kontrol: Kitap ödünç alınmadan önce stok kontrol edilir.
   ->Stok Güncelleme: Ödünç alma işleminde stok -1, iade işleminde +1 yapılır.
   ->Otomatik Ceza: İade tarihi geçtiği anda sistem gün bazlı ceza hesaplayıp ceza_sistemi tablosuna kaydeder.

Güvenlik ve Yetkilendirme
Güvenlik katmanı JWT ile sağlanmaktadır:
   -Kimlik Doğrulama: Giriş yapan kullanıcıya şifrelenmiş bir token üretilir.
   -Yetki Kontrolü: API istekleri Header'daki token üzerinden kontrol edilir.
   -Roller: 
       * KULLANICI: Kitap arama, ödünç aldıklarını görme
       * ADMIN: Veri yönetimi (CRUD), ceza takibi ve sistem konfigürasyonu.

API KullanımıSistem standart REST uç noktalarını destekler
Method	Endpoint	Açıklama
GET	/api/...	Veri listeleme ve görüntüleme
POST	/api/...	Yeni kayıt oluşturma ve ödünç alma
PUT	/api/...	Mevcut verileri güncelleme
DELETE	/api/...	Veri silme işlemleri
