import { createContext, useContext, useEffect, useState } from "react";
import authApi from "../api/authApi";

const AuthContext = createContext();

export const AuthProvider = ({ children}) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    
    const fetchMe = async () => {
        setLoading(true);
        try {
            const res = await authApi.getMe();
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
        await authApi.logout();
        setUser(null);
        await fetchMe();
    };

    return(
        <AuthContext.Provider value={{user, setUser, loading, fetchMe, logout}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext)