import { apiFetch } from "./api.js";

function hideAllSections() {
    document.querySelectorAll('#content > div')
        .forEach(d => d.style.display = 'none');
}

function moveMenuTop() {
    document.getElementById("menu").classList.add("horizontal");
    document.getElementById("userPanel").classList.add("expanded");
}

window.adminShowKitaplar = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("kitapListesi");
    div.style.display = "block";
    div.innerHTML = `
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
            <h2 style="margin: 0;">Kitap Yönetimi</h2>
            <button class="admin-add-btn" onclick="kitapEkle()"> Kitap Ekle</button>
        </div>
    `;

    const kitaplar = await apiFetch("/kitap");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Kitap Ad</th>
            <th>Yazar Ad</th>
            <th>Yazar Soyad</th>
            <th>Kategori</th>
            <th>Stok</th>
            <th>İşlem</th>
        </tr>
    `;

    kitaplar.forEach(k => {
        const r = table.insertRow();
        r.insertCell().innerText = k.kitapId;
        r.insertCell().innerText = k.kitapAd;
        r.insertCell().innerText = k.yazar.yazar_ad;
        r.insertCell().innerText = k.yazar.yazar_soyad;
        r.insertCell().innerText = k.kategori.kategoriAd;
        r.insertCell().innerText = k.stokAdedi;

        const actions = document.createElement("td");

        actions.innerHTML = `
            <button onclick="kitapGuncelle(${k.kitapId}, '${k.kitapAd}', ${k.stokAdedi}, '${k.yazar.yazar_id}', '${k.kategori.kategoriId}')">✏️</button>
            <button onclick="kitapSil(${k.kitapId})">🗑️</button>
        `;
        r.appendChild(actions);
    });

    div.appendChild(table);
};



window.adminShowYazarlar = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("yazarListesi");
    div.style.display = "block";
    div.innerHTML = `
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
        <h2 style="margin: 0;">Yazar Yönetimi</h2>
        <button  class="admin-add-btn" onclick="yazarEkle()"> Yazar Ekle</button>
    `;

    const yazarlar = await apiFetch("/yazar");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Ad</th>
            <th>Soyad</th>
            <th>İşlem</th>
        </tr>
    `;

    yazarlar.forEach(y => {
        const r = table.insertRow();
        r.insertCell().innerText = y.yazar_id;
        r.insertCell().innerText = y.yazar_ad;
        r.insertCell().innerText = y.yazar_soyad;

        r.insertCell().innerHTML = `
            <button onclick="yazarGuncelle(${y.yazar_id}, '${y.yazar_ad}', '${y.yazar_soyad}')">✏️</button>
            <button onclick="yazarSil(${y.yazar_id})">🗑️</button>
        `;
    });

    div.appendChild(table);
};


window.adminShowKullanicilar = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("kullaniciListesi");
    div.style.display = "block";
    div.innerHTML = "<h2>Kullanıcılar</h2>";

    const users = await apiFetch("/admin/kullanicilar");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Rol</th>
            <th>İşlem</th>
        </tr>
    `;

    users.forEach(u => {
        const r = table.insertRow();
        r.insertCell().innerText = u.kullaniciId;
        r.insertCell().innerText = u.email;
        r.insertCell().innerText = u.rol;

        r.insertCell().innerHTML = `
            <button onclick="kullaniciSil(${u.id})">🗑️</button>
        `;
    });

    div.appendChild(table);
};

window.adminShowCezalar = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("cezaListesi");
    div.style.display = "block";
    div.innerHTML = "<h2>Ceza Takip Sistemi</h2>";

    const cezalar = await apiFetch("/ceza/admin/tum-cezalar");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Kullanıcı (Email)</th>
            <th>Kitap Adı</th>
            <th>Ceza Miktarı</th>
        </tr>
    `;

    cezalar.forEach(c => {
        const r = table.insertRow();
        r.insertCell().innerText = c.oduncId;
        r.insertCell().innerText = c.odunc && c.odunc.kullanici ? c.odunc.kullanici.email : "Bilinmiyor";
        r.insertCell().innerText = c.odunc && c.odunc.kitap ? c.odunc.kitap.kitapAd : "Kitap Silinmiş";
        r.insertCell().innerText = c.cezaMiktari + " TL";
    });

    div.appendChild(table);
};


window.adminShowKategoriler = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("kategoriListesi");
    div.style.display = "block";
    div.innerHTML = `
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
        <h2 style="margin: 0;">Kategori Yönetimi</h2>
        <button  class="admin-add-btn" onclick="kategoriEkle()"> Kategori Ekle</button>
    `;

    const kategoriler = await apiFetch("/kategori");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Kategori Adı</th>
            <th>İşlem</th>
        </tr>
    `;

    kategoriler.forEach(k => {
        const r = table.insertRow();
        r.insertCell().innerText = k.kategoriId;
        r.insertCell().innerText = k.kategoriAd;

        r.insertCell().innerHTML = `
            <button onclick="kategoriGuncelle(${k.kategoriId}, '${k.kategoriAd}')">✏️</button>
            <button onclick="kategoriSil(${k.kategoriId})">🗑️</button>
        `;
    });

    div.appendChild(table);
};


window.adminShowOduncler = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("oduncListesi");
    div.style.display = "block";
    div.innerHTML = "<h2>Tüm Ödünçler</h2>";

    const oduncler = await apiFetch("/odunc/admin/tum-oduncler");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>Kullanıcı</th>
            <th>Kitap</th>
            <th>Alış Tarihi</th>
            <th>Planlanan İade Tarihi</th>
            <th>İade Tarihi</th>
        </tr>
    `;

    oduncler.forEach(o => {
        const r = table.insertRow();
        r.insertCell().innerText = o.kullanici.email;
        r.insertCell().innerText = o.kitap.kitapAd;
        r.insertCell().innerText = o.alinanTarih;
        r.insertCell().innerText = o.planlananIadeTarihi;
        r.insertCell().innerText = o.gercekIadeTarihi ?? "—";
    });

    div.appendChild(table);
};


// --- YÖNETİM FONKSİYONLARINI WINDOW'A BAĞLA ---

//-------KİTAP İŞLEMLERİ---------
window.kitapEkle = async function () {
    const ad = prompt("Kitap adı:");
    const stok = prompt("Stok:");
    const kategoriId = prompt("Kategori ID:");
    const yazarId = prompt("Yazar ID:");
    if (!ad || !stok) return;

    await apiFetch(`/kitap?kategoriId=${kategoriId}&yazarId=${yazarId}`, {
        method: "POST",
        body: JSON.stringify({ kitapAd: ad, stokAdedi: stok })
    });
    window.adminShowKitaplar();
};


window.kitapGuncelle = async function (id, eskiAd, eskiStok, eskiYazarId, eskiKategoriId) {
    const ad = prompt("Yeni Kitap adı:", eskiAd);
    const stok = prompt("Yeni Stok:", eskiStok);
    const kategoriId = prompt("Yeni Kategori İd:", eskiKategoriId);
    const yazarId = prompt("Yeni Yazar İd:", eskiYazarId);

    if (!ad || !stok || !yazarId || !kategoriId) return;

    await apiFetch(`/kitap/${id}?yazarId=${yazarId}&kategoriId=${kategoriId}`, {
        method: "PUT",
        body: JSON.stringify({
            kitapAd: ad,
            stokAdedi: stok
        })
    });

    alert("Kitap başarıyla güncellendi");
    window.adminShowKitaplar();
};

window.kitapSil = async function (id) {
    if (!confirm("Silmek istiyor musun?")) return;
    await apiFetch(`/kitap/${id}`, { method: "DELETE" });
    window.adminShowKitaplar();
};


// --- YAZAR İŞLEMLERİ ---

window.yazarEkle = async function () {
    const ad = prompt("Yazar adı:");
    const soyad = prompt("Yazar soyadı:");
    if (!ad || !soyad) return;
    await apiFetch("/yazar", {
        method: "POST",
        body: JSON.stringify({ yazar_ad: ad, yazar_soyad: soyad })
    });
    window.adminShowYazarlar();
};

window.yazarSil = async function (id) {
    if (!confirm("Yazar silinsin mi?")) return;
    await apiFetch(`/yazar/${id}`, { method: "DELETE" });
    window.adminShowYazarlar();
};

window.yazarGuncelle = async function (id, eskiAd, eskiSoyad) {
    const yeniAd = prompt("Yeni yazar adı:", eskiAd);
    const yeniSoyad = prompt("Yeni yazar soyadı:", eskiSoyad);

    if (!yeniAd || !yeniSoyad) return;

    await apiFetch(`/yazar/${id}`, {
        method: "PUT",
        body: JSON.stringify({
            yazar_ad: yeniAd,
            yazar_soyad: yeniSoyad
        })
    });

    alert("Yazar güncellendi");
    window.adminShowYazarlar();
};



// --- KATEGORİ İŞLEMLERİ ---

window.kategoriEkle = async function () {
    const ad = prompt("Kategori adı:");
    if (!ad) return;
    await apiFetch("/kategori", { method: "POST", body: JSON.stringify({ kategoriAd: ad }) });
    window.adminShowKategoriler();
};

window.kategoriSil = async function (id) {
    if (!confirm("Kategori silinsin mi?")) return;
    await apiFetch(`/kategori/${id}`, { method: "DELETE" });
    window.adminShowKategoriler();
};

window.kategoriGuncelle = async function (id, eskiAd) {
    const yeniAd = prompt("Yeni kategori adı:", eskiAd);

    if (!yeniAd) return;

    await apiFetch(`/kategori/${id}`, {
        method: "PUT",
        body: JSON.stringify({
            kategoriAd: yeniAd
        })
    });

    alert("Kategori güncellendi");
    window.adminShowKategoriler();
};

/// --- KULLANICI VE ÖDÜNÇ İŞLEMLERİ ---


window.kullaniciSil = async function (id) {
    if (!confirm("Kullanıcı silinsin mi?")) return;
    await apiFetch(`/admin/kullanicilar/${id}`, { method: "DELETE" });
    window.adminShowKullanicilar();
};
