import axiosClient from "./axiosClient";

const authApi = {
    login(data) {
        return axiosClient.post("/api/auth/login", data);
    },
    register(data) {
        return axiosClient.post("/api/auth/register", data);
    },
    getMe() { 
        return axiosClient.get("/api/auth/me")
    },
    logout() {
        return axiosClient.post("/api/auth/logout");
    }
}
export default authApi;