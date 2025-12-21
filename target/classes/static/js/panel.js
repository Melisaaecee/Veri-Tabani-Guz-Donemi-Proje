document.addEventListener("DOMContentLoaded", () => {
    const token = localStorage.getItem("token");
    if (!token) {
        location.href = "/index.html";
        return;
    }

    fetch("/api/auth/me", {
        headers: { "Authorization": "Bearer " + token }
    })
        .then(res => {
            if (!res.ok) throw new Error("Yetkisiz");
            return res.json();
        })
        .then(user => {
            if (user.rol === "ROLE_ADMIN") {
                loadAdminMenu();
            } else {
                loadUserMenu();
            }
        })
        .catch(err => {
            console.error(err);
            localStorage.removeItem("token");
            location.href = "/index.html";
        });
});


function loadAdminMenu() {
    document.getElementById("menu").innerHTML = `
        <button onclick="adminShowKitaplar()">Kitaplar</button>
        <button onclick="adminShowYazarlar()">Yazarlar</button>
        <button onclick="adminShowKategoriler()">Kategoriler</button>
        <button onclick="adminShowKullanicilar()">Kullanıcılar</button>
        <button onclick="adminShowOduncler()">Ödünçler</button>
        <button onclick="logout()">Çıkış</button>
    `;
}

function loadUserMenu() {
    document.getElementById("menu").innerHTML = `
        <button onclick="showKitaplar()">Kitaplar</button>
        <button onclick="showYazarlar()">Yazarlar</button>
        <button onclick="showKategoriler()">Kategoriler</button>
        <button onclick="showOduncler()">Ödünçlerim</button>
        <button onclick="showIadeler()">İadelerim</button>
        <button onclick="showCezalar()">Cezalarım</button>
        <button onclick="logout()">Çıkış</button>
    `;
}

function logout() {
    localStorage.removeItem("token");
    location.href = "/index.html";
}
