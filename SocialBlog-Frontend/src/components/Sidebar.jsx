import { Link } from "react-router-dom";
import { useNotification } from "../context/NotificationContext";

export default function Sidebar() {
  const { unreadCount } = useNotification();

  return (
    <aside className="w-64 bg-gray-50 border-r p-4">
      <ul className="space-y-3">
        <li><Link to="/">🏠 Bảng tin</Link></li>
        <li><Link to="/friends">👥 Bạn bè</Link></li>
        <li><Link to="/messages">💬 Tin nhắn</Link></li>

        <li className="flex justify-between items-center">
          <Link to="/notifications">🔔 Thông báo</Link>
          {unreadCount > 0 && (
            <span className="bg-red-500 text-white text-xs px-2 rounded-full">
              {unreadCount}
            </span>
          )}
        </li>

        <li><Link to="/profile">🙍 Trang cá nhân</Link></li>
      </ul>
    </aside>
  );
}
