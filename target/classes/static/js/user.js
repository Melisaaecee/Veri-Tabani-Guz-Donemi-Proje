import { apiFetch } from "./api.js";

function hideAllSections() {
    document.querySelectorAll('#content > div')
        .forEach(d => d.style.display = 'none');
}

function moveMenuTop() {
    document.getElementById("menu").classList.add("horizontal");
    document.getElementById("userPanel").classList.add("expanded");
}

function createUserTableContainer(targetId, title) {
    const div = document.getElementById(targetId);
    div.style.display = "block";
    div.innerHTML = `
        <h2 style="margin-bottom: 20px; color: #333;">${title}</h2>
        <div id="${targetId}Scroll" class="table-container"></div>
    `;
    return document.getElementById(`${targetId}Scroll`);
}

window.showKitaplar = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollArea = createUserTableContainer("kitapListesi", "Kitaplar");

    const kitaplar = await apiFetch("/kitap");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead>
        <tr>
            <th>ID</th>
            <th>Kitap Adı</th>
            <th>Yazar</th>
            <th>Kategori</th>
            <th>Stok</th>
            <th>İşlem</th>
        </tr>
        </thead> <tbody></tbody>
    `;

    const tbody = table.querySelector("tbody");
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
    scrollArea.appendChild(table);
};

window.showYazarlar = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollArea = createUserTableContainer("yazarListesi", "Yazarlar");

    const yazarlar = await apiFetch("/yazar");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead>
        <tr>
            <th>Yazar ID</th>
            <th>Yazar Ad</th>
            <th>Yazar Soyad</th>
        </tr>
        </thead> <tbody></tbody>
    `;

    const tbody = table.querySelector("tbody");
    yazarlar.forEach(y => {
        const r = table.insertRow();
        r.insertCell().innerText = y.yazar_id;
        r.insertCell().innerText = y.yazar_ad;
        r.insertCell().innerText = y.yazar_soyad;
    });

    scrollArea.appendChild(table);
};


window.showKategoriler = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollArea = createUserTableContainer("kategoriListesi", "Kategoriler");

    const kategoriler = await apiFetch('/kategori');
    const table = document.createElement('table');
    table.className = "data-table";
    table.innerHTML = `
    <thead>
    <tr>
    <th>ID</th>
    <th>Kategoriler</th>
   </tr>
        </thead> <tbody></tbody>
    
    `;

    const tbody = table.querySelector("tbody");
    kategoriler.forEach(k => {
        const r = table.insertRow();
        r.insertCell().innerText = k.kategoriId;
        r.insertCell().innerText = k.kategoriAd;
    });

    scrollArea.appendChild(table);
};


window.showOduncler = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollArea = createUserTableContainer("oduncListesi", "Ödünçler Aldıklarım");

    const oduncler = await apiFetch("/odunc/aktif");
    const table = document.createElement("table");
    table.className = "data-table";
    table.innerHTML = `<thead>
        <tr>
            <th>Ödünç ID</th>
            <th>Kitap</th>
            <th>Alış Tarihi</th>
            <th>Planlanan İade</th>
            <th>İşlem</th>
        </tr>
        </thead> <tbody></tbody>
    `;

    const tbody = table.querySelector("tbody");
    if (oduncler.length === 0) {
        scrollArea.innerHTML = "<p class='no-data'>Şu an üzerinizde kitap bulunmamaktadır.</p>";
        return;
    }

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

    scrollArea.appendChild(table);
};


window.showCezalar = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollArea = createUserTableContainer("cezaListesi", "Cezalarım");

    const cezalar = await apiFetch('/ceza/benim');
    if (!cezalar || cezalar.length === 0) {
       scrollArea.innerHTML = "<p class='no-data'>Cezanız Bulunmamaktadır</p>";
        return;
    }

    const table = document.createElement('table');
    table.className = "data-table";
    table.innerHTML = `<thead>
    <tr><th>Kitap</th>
    <th>Ceza</th> </tr>
        </thead> <tbody></tbody>
    `;

    const tbody = table.querySelector("tbody");
    cezalar.forEach(c => {
        const row = table.insertRow();
        row.insertCell().innerText = c.odunc.kitap.kitapAd;
        row.insertCell().innerText = c.cezaMiktari;
    });
    scrollArea.appendChild(table);
};

window.showIadeler = async function () {
    hideAllSections();
    moveMenuTop();
    const scrollArea = createUserTableContainer("iadeListesi", "İadelerim");

    const oduncler = await apiFetch('/odunc/gecmis');
    if (!oduncler || oduncler.length === 0) {
        scrollArea.innerHTML = "<p class='no-data'>Henüz iade edilmiş bir kitabınız bulunmuyor.</p>";
        return;
    }
    const table = document.createElement('table');
    table.className = "data-table";
    table.innerHTML = `<thead>
        <tr>
            <th>Ödünç ID</th>
            <th>Kitap</th>
            <th>Alış Tarihi</th>
            <th>İade Tarihi</th>
        </tr>
        </thead> <tbody></tbody>
    `;

    const tbody = table.querySelector("tbody");
    oduncler.forEach(o => {
        const row = table.insertRow();
        row.insertCell().innerText = o.oduncId;
        row.insertCell().innerText = o.kitap.kitapAd;
        row.insertCell().innerText = o.alinanTarih;
        row.insertCell().innerText = o.gercekIadeTarihi;
    });

    scrollArea.appendChild(table);
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
