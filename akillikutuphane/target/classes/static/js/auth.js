// auth.js

const API_URL = "http://localhost:8080/api/auth";

// Login fonksiyonu
async function login(email, sifre) {
    const response = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, sifre })
    });

    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message || "Giriş yapılamadı");
    }

    const data = await response.json();
    // Token döndür
    return data.token;
}

// Register fonksiyonu
async function register(user) {
    const response = await fetch(`${API_URL}/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });

    if (!response.ok) {
        const err = await response.json();
        throw new Error(err.message || "Kayıt yapılamadı");
    }

    return await response.json();
}

// Token’dan rol çekmek
function parseJwt(token) {
    if (!token) return null;
    const payload = token.split('.')[1];
    return JSON.parse(atob(payload));
}

// Logout
function logout() {
    localStorage.removeItem("token");
    window.location.href = "index.html";
}
