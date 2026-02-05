import { Link, useNavigate } from "react-router-dom";
import { FcGoogle } from "react-icons/fc";
import authApi from "../../api/authApi";
import { useState } from "react";

export default function Register() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    username: "",
    password: "",
    email: "",
    fullName: "",
    avatar: ""
  });

  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      await authApi.register(form);
      alert("Đăng ký thành công! Vui lòng đăng nhập.");
      navigate("/login", {replace: true});
    } catch (err) {
      setError(err.response?.data?.message || err.response?.data || "Đăng ký thất bại");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <form
        onSubmit={handleSubmit}
        className="w-full max-w-md p-6 bg-white border rounded"
      >
        <h2 className="mb-4 text-xl font-bold text-center">Đăng ký</h2>

        <input
          name="username"
          className="w-full p-2 mb-3 border rounded"
          placeholder="Username"
          onChange={handleChange}
          required
        />

        <input
          type="password"
          name="password"
          className="w-full p-2 mb-3 border rounded"
          placeholder="Password"
          onChange={handleChange}
          required
        />

        <input
          type="email"
          name="email"
          className="w-full p-2 mb-3 border rounded"
          placeholder="Email"
          onChange={handleChange}
          required
        />

        <input
          name="fullName"
          className="w-full p-2 mb-3 border rounded"
          placeholder="Họ tên"
          onChange={handleChange}
          required
        />

        <input
          name="avatar"
          className="w-full p-2 mb-3 border rounded"
          placeholder="Avatar URL"
          onChange={handleChange}
        />

        {error && (
          <p className="mb-3 text-sm text-center text-red-500">{error}</p>
        )}

        <button
          type="submit"
          className="w-full py-2 text-white bg-blue-600 rounded hover:bg-blue-700"
        >
          Tạo tài khoản
        </button>
        {error && (
          <p className="mb-3 text-sm text-center text-red-500">{error}</p>
        )}
        <p className="p-4 text-center">=============== Or ===============</p>

        <button
          type="button"
          className="flex items-center justify-center w-full gap-2 py-2 border-2 border-blue-500 rounded"
        >
          <FcGoogle size={24} />
          <span>Đăng ký bằng Google</span>
        </button>

        <p className="mt-3 text-sm text-center">
          Đã có tài khoản?{" "}
          <Link to="/login" className="text-blue-600">
            Đăng nhập
          </Link>
        </p>
      </form>
    </div>
  );
}
