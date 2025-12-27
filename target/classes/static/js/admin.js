import { apiFetch } from "./api.js";

function hideAllSections() {
    document.querySelectorAll('#content > div')
        .forEach(d => d.style.display = 'none');
}

function moveMenuTop() {
    document.getElementById("menu").classList.add("horizontal");
    document.getElementById("userPanel").classList.add("expanded");
}

function createTableContainer(targetDivId, title, hasAddButton = false, addFunction = "") {
    const div = document.getElementById(targetDivId);
    div.style.display = "block";

    let headerHtml = `
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
            <h2 style="margin: 0;">${title}</h2>`;

    if (hasAddButton) {
        headerHtml += `<button class="admin-add-btn" onclick="${addFunction}">+ Ekle</button>`;
    }

    headerHtml += `</div><div id="${targetDivId}Scroll" class="table-container"></div>`;
    div.innerHTML = headerHtml;
    return document.getElementById(`${targetDivId}Scroll`);
}

// --- KİTAPLAR ---
window.adminShowKitaplar = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollContainer = createTableContainer("kitapListesi", "Kitap Yönetimi", true, "kitapEkle()");

    const kitaplar = await apiFetch("/kitap");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <thead>
            <tr>
                <th>ID</th><th>Kitap Ad</th><th>Yazar</th><th>Kategori</th><th>Stok</th><th>İşlem</th>
            </tr>
        </thead>
        <tbody></tbody>`;

    const tbody = table.querySelector("tbody");
    kitaplar.forEach(k => {
        const r = tbody.insertRow();
        r.insertCell().innerText = k.kitapId;
        r.insertCell().innerText = k.kitapAd;
        r.insertCell().innerText = k.yazar ? `${k.yazar.yazar_ad} ${k.yazar.yazar_soyad}` : "—";
        r.insertCell().innerText = k.kategori ? k.kategori.kategoriAd : "—";
        r.insertCell().innerText = k.stokAdedi;
        r.insertCell().innerHTML = `
            <button onclick="kitapGuncelle(${k.kitapId}, '${k.kitapAd}', ${k.stokAdedi}, ${k.yazar?.yazar_id}, ${k.kategori?.kategoriId})">✏️</button>
            <button onclick="kitapSil(${k.kitapId})">🗑️</button>`;
    });
    scrollContainer.appendChild(table);
};

// --- YAZARLAR ---
window.adminShowYazarlar = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollContainer = createTableContainer("yazarListesi", "Yazar Yönetimi", true, "yazarEkle()");

    const yazarlar = await apiFetch("/yazar");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead><tr><th>ID</th><th>Ad</th><th>Soyad</th><th>İşlem</th></tr></thead><tbody></tbody>`;

    const tbody = table.querySelector("tbody");
    yazarlar.forEach(y => {
        const r = tbody.insertRow();
        r.insertCell().innerText = y.yazar_id;
        r.insertCell().innerText = y.yazar_ad;
        r.insertCell().innerText = y.yazar_soyad;
        r.insertCell().innerHTML = `
            <button onclick="yazarGuncelle(${y.yazar_id}, '${y.yazar_ad}', '${y.yazar_soyad}')">✏️</button>
            <button onclick="yazarSil(${y.yazar_id})">🗑️</button>`;
    });
    scrollContainer.appendChild(table);
};

// --- KATEGORİLER ---
window.adminShowKategoriler = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollContainer = createTableContainer("kategoriListesi", "Kategori Yönetimi", true, "kategoriEkle()");

    const kategoriler = await apiFetch("/kategori");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead><tr><th>ID</th><th>Kategori Adı</th><th>İşlem</th></tr></thead><tbody></tbody>`;

    const tbody = table.querySelector("tbody");
    kategoriler.forEach(k => {
        const r = tbody.insertRow();
        r.insertCell().innerText = k.kategoriId;
        r.insertCell().innerText = k.kategoriAd;
        r.insertCell().innerHTML = `
            <button onclick="kategoriGuncelle(${k.kategoriId}, '${k.kategoriAd}')">✏️</button>
            <button onclick="kategoriSil(${k.kategoriId})">🗑️</button>`;
    });
    scrollContainer.appendChild(table);
};

// --- KULLANICILAR ---
window.adminShowKullanicilar = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollContainer = createTableContainer("kullaniciListesi", "Kullanıcı Yönetimi");

    const users = await apiFetch("/admin/kullanicilar");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead><tr><th>ID</th><th>Email</th><th>Rol</th><th>İşlem</th></tr></thead><tbody></tbody>`;

    const tbody = table.querySelector("tbody");
    users.forEach(u => {
        const r = tbody.insertRow();
        r.insertCell().innerText = u.kullaniciId;
        r.insertCell().innerText = u.email;
        r.insertCell().innerText = u.rol;
        r.insertCell().innerHTML = `<button onclick="kullaniciSil(${u.kullaniciId})">🗑️</button>`;
    });
    scrollContainer.appendChild(table);
};

// --- CEZALAR ---
window.adminShowCezalar = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollContainer = createTableContainer("cezaListesi", "Ceza Takip Sistemi");

    const cezalar = await apiFetch("/ceza/admin/tum-cezalar");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead><tr><th>ID</th><th>Kullanıcı (Email)</th><th>Kitap Adı</th><th>Ceza Miktarı</th></tr></thead><tbody></tbody>`;

    const tbody = table.querySelector("tbody");
    cezalar.forEach(c => {
        const r = tbody.insertRow();
        r.insertCell().innerText = c.oduncId;
        r.insertCell().innerText = c.odunc?.kullanici?.email || "Bilinmiyor";
        r.insertCell().innerText = c.odunc?.kitap?.kitapAd || "Kitap Silinmiş";
        r.insertCell().innerText = c.cezaMiktari + " TL";
    });
    scrollContainer.appendChild(table);
};

// --- ÖDÜNÇLER ---
window.adminShowOduncler = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollContainer = createTableContainer("oduncListesi", "Tüm Ödünçler");

    const oduncler = await apiFetch("/odunc/admin/tum-oduncler");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead><tr><th>Kullanıcı</th><th>Kitap</th><th>Alış Tarihi</th><th>Planlanan İade</th><th>Gerçek İade</th></tr></thead><tbody></tbody>`;

    const tbody = table.querySelector("tbody");
    oduncler.forEach(o => {
        const r = tbody.insertRow();
        r.insertCell().innerText = o.kullanici.email;
        r.insertCell().innerText = o.kitap.kitapAd;
        r.insertCell().innerText = o.alinanTarih;
        r.insertCell().innerText = o.planlananIadeTarihi;
        r.insertCell().innerText = o.gercekIadeTarihi ?? "—";
    });
    scrollContainer.appendChild(table);
};

// --- YÖNETİM FONKSİYONLARI ---

window.kitapEkle = async function () {
    const ad = prompt("Kitap adı:");
    const stok = prompt("Stok:");
    const kategoriId = prompt("Kategori ID:");
    const yazarId = prompt("Yazar ID:");
    if (!ad || !stok) return;

    await apiFetch(`/kitap?kategoriId=${kategoriId}&yazarId=${yazarId}`, {
        method: "POST",
        body: JSON.stringify({ kitapAd: ad, stokAdedi: parseInt(stok) })
    });
    window.adminShowKitaplar();
};

window.kitapGuncelle = async function (id, eskiAd, eskiStok, yazarId, kategoriId) {
    const ad = prompt("Yeni Kitap adı:", eskiAd);
    const stok = prompt("Yeni Stok:", eskiStok);
    if (!ad || !stok) return;

    await apiFetch(`/kitap/${id}?yazarId=${yazarId}&kategoriId=${kategoriId}`, {
        method: "PUT",
        body: JSON.stringify({ kitapAd: ad, stokAdedi: parseInt(stok) })
    });
    window.adminShowKitaplar();
};

window.kitapSil = async function (id) {
    if (!confirm("Silmek istiyor musun?")) return;
    await apiFetch(`/kitap/${id}`, { method: "DELETE" });
    window.adminShowKitaplar();
};

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
        body: JSON.stringify({ yazar_ad: yeniAd, yazar_soyad: yeniSoyad })
    });
    window.adminShowYazarlar();
};

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
    await apiFetch(`/kategori/${id}`, { method: "PUT", body: JSON.stringify({ kategoriAd: yeniAd }) });
    window.adminShowKategoriler();
};

window.kullaniciSil = async function (id) {
    if (!confirm("Kullanıcı silinsin mi?")) return;
    await apiFetch(`/admin/kullanicilar/${id}`, { method: "DELETE" });
    window.adminShowKullanicilar();
};