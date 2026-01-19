import { createContext, useContext, useEffect, useState } from "react";
import axios from "axios";  

const AuthContext = createContext();

export const AuthProvider = ({ children}) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const fetchMe = async () => {
        try {
            const res = await axios.get("http://localhost:8888/api/auth/me", {
                withCredentials: true
            });
            setUser(res.data);
        } catch {
            setUser(null);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchMe();
    },[]);

    // Đăng xuất handle
    const logout = () => {
        setUser(null);
        localStorage.removeItem("token");
    };

    return(
        <AuthContext.Provider value={{user, setUser, logout, loading}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext)