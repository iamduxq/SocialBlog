import { Link } from "react-router-dom";

export default function Header() {
  return (
    <header className="bg-blue-600 text-white p-4 flex justify-between">
      <h1 className="text-xl font-bold">SocialBlog</h1>
      <nav className="space-x-4">
        <Link to="/">Bảng tin</Link>
        <Link to="/friends">Bạn bè</Link>
        <Link to="/profile">Profile</Link>
      </nav>
    </header>
  );
}
