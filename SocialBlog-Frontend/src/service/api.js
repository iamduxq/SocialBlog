import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8888/api",
    withCredentials: true,
});

export const login = (data) => api.post("/auth/login", data);
export const register = (data) => api.post("/auth/register", data);
export const getMe = () => api.get("/auth/me");
export const logout = () => api.post("/auth/logout");

export default api;