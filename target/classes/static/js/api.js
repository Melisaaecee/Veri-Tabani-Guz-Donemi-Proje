
const BASE_URL = "http://localhost:8080/api";

export async function apiFetch(endpoint, options = {}) {
    const token = localStorage.getItem("token");

    const headers = {
        "Content-Type": "application/json",
        ...(options.headers || {})
    };

    if (token) {
        headers["Authorization"] = `Bearer ${token}`;
    }

    const res = await fetch(BASE_URL + endpoint, {
        ...options,
        headers
    });

    if (!res.ok) {
        const text = await res.text();
        throw new Error(`API ${res.status}: ${text}`);
    }

    // void dönen endpointler için
    if (res.status === 204) return null;

    return res.json();
}
