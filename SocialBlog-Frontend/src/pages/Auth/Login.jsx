import { Link } from "react-router-dom";

export default function Login() {
  return (
    <div className="max-w-md mx-auto bg-white border p-6 rounded">
      <h2 className="text-xl font-bold mb-4">Đăng nhập</h2>
      <input className="border w-full p-2 mb-3" placeholder="Username" />
      <input type="password" className="border w-full p-2 mb-3" placeholder="Password" />
      <button className="w-full bg-blue-600 text-white py-2 rounded">
        Đăng nhập
      </button>
      <p className="text-sm mt-3">
        Chưa có tài khoản? <Link to="/register" className="text-blue-600">Đăng ký</Link>
      </p>
    </div>
  );
}
