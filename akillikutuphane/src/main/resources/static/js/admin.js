// admin.js
async function getKitaplar() {
    return await apiFetch("/kitap");
}

async function addKitap(kitap, kategoriId, yazarId) {
    return await apiFetch(`/kitap?kategoriId=${kategoriId}&yazarId=${yazarId}`, {
        method: "POST",
        body: JSON.stringify(kitap)
    });
}

async function deleteKitap(kitapId) {
    if (!confirm("Bu kitabı silmek istediğinize emin misiniz?")) return;
    await apiFetch(`/kitap/${kitapId}`, { method: "DELETE" });
    alert("Kitap silindi");
    loadKitaplar();
}

// Tabloyu yükleme
async function loadKitaplar() {
    const kitaplar = await getKitaplar();
    const table = document.getElementById("kitapTable");
    table.innerHTML = `
        <tr>
            <th>ID</th><th>Ad</th><th>Stok</th><th>Kategori</th><th>Yazar</th><th>Sil</th>
        </tr>`;
    kitaplar.forEach(k => {
        const row = table.insertRow();
        row.insertCell(0).innerText = k.kitapId;
        row.insertCell(1).innerText = k.kitapAd;
        row.insertCell(2).innerText = k.stokAdedi;
        row.insertCell(3).innerText = k.kategori.kategoriAd;
        row.insertCell(4).innerText = k.yazar.yazar_ad;
        const del = row.insertCell(5);
        const btn = document.createElement("button");
        btn.innerText = "Sil";
        btn.onclick = () => deleteKitap(k.kitapId);
        del.appendChild(btn);
    });
}

loadKitaplar();
