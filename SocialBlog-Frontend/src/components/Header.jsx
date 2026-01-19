import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function Header() {
  const {user, logout} = useAuth();
  return (
    <header className="flex justify-between p-4 text-white bg-blue-600">
      <Link to="/"><h1 className="text-xl font-bold">SocialBlog</h1></Link>
      <nav className="space-x-4">
        <Link to="/">Bảng tin</Link>
        <Link to="/friends">Bạn bè</Link>
        <Link to="/profile">Profile</Link>
        {!user && (
          <>
          <Link to="/login">Login</Link>
          <Link to="/register">Register</Link>
          </>
        )}
        {user && (
          <div className="flex items-center gap-3">
            {user.avatar && (
              <img src={user.avatar} alt="avatar" className="w-8 h-8 rounded-full"/>
            )}
            <span className="font-semibold">{user.fullName}</span>
            <button onClick={logout} className="px-2 py-1 text-sm bg-red-500 rounded hover:bg-red-600">
              Logout
            </button>
          </div>
        )}
      </nav>
    </header>
  );
}
