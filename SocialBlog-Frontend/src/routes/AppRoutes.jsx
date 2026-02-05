import { Routes, Route } from "react-router-dom";
import MainLayout from "../layouts/MainLayout";
import Feed from "../pages/Feed/Feed";
import Friends from "../pages/Friends/Friends";
import Profile from "../pages/Profile/Profile";
import Messages from "../pages/Message/Messages";
import Notifications from "../pages/Notifications/Notifications";
import Login from "../pages/Auth/Login";
import Register from "../pages/Auth/Register";
import OAuth2Redirect from "../pages/Auth/OAuth2Redirect";
import ProtectedRoute from "./ProtectedRoute";

export default function AppRoutes() {
    return (
        <Routes>
            <Route element={<ProtectedRoute />}>
                <Route path="/" element={<MainLayout><Feed /></MainLayout>} />
                <Route path="/friends" element={<MainLayout><Friends /></MainLayout>} />
                <Route path="/profile" element={<MainLayout><Profile /></MainLayout>} />
                <Route path="/messages" element={<MainLayout><Messages /></MainLayout>} />
                <Route path="/notifications" element={<MainLayout><Notifications /></MainLayout>} />    
            </Route>

            {/* OAuth2 */}
            <Route path="/oauth2/redirect" element={<OAuth2Redirect />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
        </Routes>
    );
}