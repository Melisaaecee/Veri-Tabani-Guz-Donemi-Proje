# Akıllı Kütüphane Yönetim Sistemi
> **Veri Tabanı Güz Dönemi Projesi**

Bu proje, modern yazılım geliştirme prensiplerine (**Katmanlı Mimari**) uygun olarak geliştirilmiş, uçtan uca bir Kütüphane Yönetim Sistemi'dir.

---

##  Projenin Amacı
Projenin temel hedefi; kitap takibi, ödünç alma, iade ve ceza süreçlerini otomatize ederek kütüphane yönetimini dijitalleştirmektir.

## 🛠 Teknik Kazanımlar
* **İlişkisel Veritabanı:** 3. Normal Form (3NF) kurallarına uygun tasarım.
* **Trigger Kullanımı:** Veritabanı seviyesinde otomatik ceza hesaplama ve stok yönetimi.
* **Katmanlı Mimari:** Sürdürülebilir ve test edilebilir kod yapısı.
* **Güvenlik:** JWT ile rol bazlı (Admin/Kullanıcı) yetkilendirme.
* **Harici Entegrasyon:** Geciken iadeler için otomatik e-posta bildirim sistemi.

---

##  Teknik Mimari
Projenin backend yapısı 4 ana katmandan oluşur:



1. **Entity Katmanı:** Veritabanı tablolarının Java modelleri.
2. **Repository Katmanı:** Veri erişim (CRUD) operasyonları.
3. **Service Katmanı:** İş mantığının yürütüldüğü ana merkez.
4. **Controller Katmanı:** REST API uç noktalarının yönetimi.

---

## Veritabanı Tasarımı (3NF)


### Tetikleyiciler (Triggers)
Veritabanı düzeyinde aşağıdaki işlemler otomatik olarak gerçekleşir:
* **Stok Kontrol:** Ödünç alma öncesi miktar kontrolü.
* **Stok Güncelleme:** İşlem türüne göre stokta otomatik artış/azalış.
* **Otomatik Ceza:** Geciken iadelerde gün bazlı ceza hesaplama.

---

##  Güvenlik ve Yetkilendirme (JWT)
* **KULLANICI:** Kitap arama ve kişisel işlem takibi.
* **ADMIN:** Tam yetki (CRUD), ceza yönetimi ve sistem ayarları.

---

## API Kullanımı
| Metot | Endpoint | Açıklama |
| :--- | :--- | :--- |
| `GET` | `/api/...` | Veri listeleme ve görüntüleme |
| `POST` | `/api/...` | Yeni kayıt ve ödünç alma |
| `PUT` | `/api/...` | Veri güncelleme |
| `DELETE` | `/api/...` | Veri silme |

---

## 💻 Kullanılan Teknolojiler
* **Backend:** Java, Spring Boot
* **Frontend:** JavaScript, HTML, CSS
* **Veritabanı:** MySQL 
* **Güvenlik:** Spring Security & JWT
