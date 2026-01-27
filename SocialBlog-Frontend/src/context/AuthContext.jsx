import { createContext, useContext, useEffect, useState } from "react";
import * as api from "../service/api";

const AuthContext = createContext();

export const AuthProvider = ({ children}) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    
    const fetchMe = async () => {
        try {
            const res = await api.getMe();
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
    const logout = async () => {
        await api.logout();
        setUser(null);
    };

    return(
        <AuthContext.Provider value={{user, setUser, loading, fetchMe, logout}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext)