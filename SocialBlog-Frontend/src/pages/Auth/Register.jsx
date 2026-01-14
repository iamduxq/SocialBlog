export default function Register() {
  return (
    <div className="max-w-md mx-auto bg-white border p-6 rounded">
      <h2 className="text-xl font-bold mb-4">Đăng ký</h2>
      <input className="border w-full p-2 mb-3" placeholder="Username" />
      <input className="border w-full p-2 mb-3" placeholder="Email" />
      <input type="password" className="border w-full p-2 mb-3" placeholder="Password" />
      <button className="w-full bg-green-600 text-white py-2 rounded">
        Tạo tài khoản
      </button>
    </div>
  );
}
