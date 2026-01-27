import { useEffect, useRef } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

export default function OAuth2Redirect() {
    const [params] = useSearchParams();
    const navigate = useNavigate();
    const {fetchMe} = useAuth;
    const handleRef = useRef(false);

    useEffect(() => {
        if (handleRef.current) return;
        handleRef.current = true;

        const token = params.get("token");
        if (!token) {
            navigate("/login", { replace: true });
            return;
        }
        fetchMe().then(() => {
            navigate("/", { replace: true });
        });
    }, []);
    return <p>Đang đăng nhập bằng Google...</p>
}