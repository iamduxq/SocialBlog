import { Link } from "react-router-dom";
import axios from "axios";
import { FcGoogle } from 'react-icons/fc';
import { useState } from "react";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    try {
      axios.post("http://localhost:8888/api/auth/login",
        {username, password},
        {withCredentials: true}
      );
      alert("Đăng nhập thành công!");
      window.location.href = "/";
    } catch (err) {
      setError("Tài khoản hoặc mật khẩu không chính xác!");
    }
  }

  const loginWithGoogle = () => {
    window.location.href = "http://localhost:8888/oauth2/authorization/google";
  };

  return (
      <div className="flex items-center justify-center min-h-screen bg-gray-100">
        <form
          onSubmit={handleLogin}
          className="flex flex-col w-full max-w-md p-6 bg-white border rounded"
        >
          <h2 className="mb-4 text-xl font-bold text-center">
            Đăng nhập
          </h2>

          <input
            className="w-full p-2 mb-3 border rounded"
            placeholder="Username or Email"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />

          <input
            type="password"
            className="w-full p-2 mb-2 border rounded"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <Link to="#" className="mb-4 text-sm text-right text-blue-600 underline">
            Forgot Password?
          </Link>

          <button type="submit" className="w-full py-2 text-white bg-blue-600 rounded hover:bg-blue-700">
            Đăng nhập
          </button>

          <p className="p-4 text-center">=============== Or ===============</p>

          <button
            onClick={loginWithGoogle}
            className="flex items-center justify-center w-full gap-2 py-2 border-2 border-blue-500 rounded"
          >
            <FcGoogle size={24} />
            <span>Đăng nhập bằng Google</span>
          </button>

          <p className="mt-3 text-sm text-center">
            Chưa có tài khoản?{" "}
            <Link to="/register" className="text-blue-600">
              Đăng ký
            </Link>
          </p>
        </form>
      </div>
  );
}
export default Login;
