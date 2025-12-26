import { apiFetch } from "./api.js";

function hideAllSections() {
    document.querySelectorAll('#content > div')
        .forEach(d => d.style.display = 'none');
}

function moveMenuTop() {
    document.getElementById("menu").classList.add("horizontal");
    document.getElementById("userPanel").classList.add("expanded");
}

window.showKitaplar = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("kitapListesi");
    div.style.display = "block";
    div.innerHTML = "<h2>Kitaplar</h2>";

    const kitaplar = await apiFetch("/kitap");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Kitap Adı</th>
            <th>Yazar</th>
            <th>Kategori</th>
            <th>Stok</th>
            <th>İşlem</th>
        </tr>
    `;

    kitaplar.forEach(k => {
        const row = table.insertRow();
        row.insertCell().innerText = k.kitapId;
        row.insertCell().innerText = k.kitapAd;
        row.insertCell().innerText = `${k.yazar.yazar_ad} ${k.yazar.yazar_soyad}`;
        row.insertCell().innerText = k.kategori.kategoriAd;
        row.insertCell().innerText = k.stokAdedi;

        const btn = document.createElement("button");
        btn.innerText = "Ödünç Al";
        btn.className = "btn-odunc"; // ödünç al
        btn.onclick = () => oduncAl(k.kitapId,
            prompt("İade tarihi (YYYY-MM-DD)"));
        row.insertCell().appendChild(btn);
    });

    div.appendChild(table);
};

window.showYazarlar = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("yazarListesi");
    div.style.display = "block";
    div.innerHTML = "<h2>Yazarlar</h2>";

    const yazarlar = await apiFetch("/yazar");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>Yazar ID</th>
            <th>Yazar Ad</th>
            <th>Yazar Soyad</th>
        </tr>
    `;

    yazarlar.forEach(y => {
        const r = table.insertRow();
        r.insertCell().innerText = y.yazar_id;
        r.insertCell().innerText = y.yazar_ad;
        r.insertCell().innerText = y.yazar_soyad;
    });

    div.appendChild(table);
};


window.showKategoriler = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById('kategoriListesi');
    div.style.display = 'block';
    div.innerHTML = '<h2>Kategoriler</h2>';

    const kategoriler = await apiFetch('/kategori');

    const table = document.createElement('table');
    table.className = "data-table";
    table.innerHTML = `<tr><th>ID</th><th>Kategoriler</th></tr>`;

    kategoriler.forEach(k => {
        const r = table.insertRow();
        r.insertCell().innerText = k.kategoriId;
        r.insertCell().innerText = k.kategoriAd;
    });

    div.appendChild(table);
};


window.showOduncler = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById("oduncListesi");
    div.style.display = "block";
    div.innerHTML = "<h2>Ödünç Aldıklarım</h2>";

    const oduncler = await apiFetch("/odunc/aktif");

    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>Ödünç ID</th>
            <th>Kitap</th>
            <th>Alış Tarihi</th>
            <th>Planlanan İade</th>
            <th>İşlem</th>
        </tr>
    `;

    oduncler.forEach(o => {
        const row = table.insertRow();
        row.insertCell().innerText = o.oduncId;
        row.insertCell().innerText = o.kitap.kitapAd;
        row.insertCell().innerText = o.alinanTarih;
        row.insertCell().innerText = o.planlananIadeTarihi;

        const btn = document.createElement("button");
        btn.innerText = "İade Et";
        btn.className = "btn-iade"; // iade et
        btn.onclick = () => kitapIade(o.oduncId);

        row.insertCell().appendChild(btn);
    });

    div.appendChild(table);
};


window.showCezalar = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById('cezaListesi');
    div.style.display = 'block';
    div.innerHTML = '<h2>Ceza Puanlarım</h2>';

    const cezalar = await apiFetch('/ceza/benim');

    if (!cezalar || cezalar.length === 0) {
        div.innerHTML += "<p>Ceza yok.</p>";
        return;
    }

    const table = document.createElement('table');
    table.className = "data-table";
    table.innerHTML = `
    <tr><th>Kitap</th>
    <th>Ceza</th></tr>
    `;

    cezalar.forEach(c => {
        const row = table.insertRow();
        row.insertCell().innerText = c.odunc.kitap.kitapAd;
        row.insertCell().innerText = c.cezaMiktari;
    });

    div.appendChild(table);
};

window.showIadeler = async function () {
    hideAllSections();
    moveMenuTop();

    const div = document.getElementById('iadeListesi');
    div.style.display = 'block';
    div.innerHTML = '<h2>İade Ettiğim Kitaplar</h2>';

    const oduncler = await apiFetch('/odunc/gecmis');

    if (!oduncler || oduncler.length === 0) {
        div.innerHTML += "<p>Henüz iade edilmiş kitap yok.</p>";
        return;
    }

    const table = document.createElement('table');
    table.className = "data-table";
    table.innerHTML = `
        <tr>
            <th>Ödünç ID</th>
            <th>Kitap</th>
            <th>Alış Tarihi</th>
            <th>İade Tarihi</th>
            
        </tr>
    `;

    oduncler.forEach(o => {
        const row = table.insertRow();
        row.insertCell().innerText = o.oduncId;
        row.insertCell().innerText = o.kitap.kitapAd;
        row.insertCell().innerText = o.alinanTarih;
        row.insertCell().innerText = o.gercekIadeTarihi;
    });

    div.appendChild(table);
};

// Çıkış yap
window.logout = function () {
    localStorage.removeItem('token');
    window.location.href = 'index.html';
};

// Ödünç alma ve iade fonksiyonları
async function oduncAl(kitapId, tarih) {
    await apiFetch(`/odunc/al?kitapId=${kitapId}&planlananIadeTarihi=${tarih}`, {
        method: "POST"
    });
    alert("Kitap ödünç alındı");
    showOduncler();
}

async function kitapIade(oduncId) {
    const token = localStorage.getItem("token");
    
    const response = await fetch(`/api/odunc/iade/${oduncId}`, {
        method: "PUT",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    if (response.ok) {
        alert("İade yapıldı");
        showOduncler();
        if (typeof showIadeler === "function") showIadeler();
    } else {
        alert("Hata oluştu, kod: " + response.status);
    }
}
