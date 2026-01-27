import { Link } from "react-router-dom";
import { useNotification } from "../context/NotificationContext";

export default function Sidebar() {
  const { unreadCount } = useNotification();

  return (
    <aside className="w-64 p-4 border-r bg-gray-50">
      <ul className="space-y-3">
        <li><Link to="/">🏠 Bảng tin</Link></li>
        <li><Link to="/friends">👥 Bạn bè</Link></li>
        <li><Link to="/messages">💬 Tin nhắn</Link></li>

        <li className="flex items-center justify-between">
          <Link to="/notifications">🔔 Thông báo</Link>
          {unreadCount > 0 && (
            <span className="px-2 text-xs text-white bg-red-500 rounded-full">
              {unreadCount}
            </span>
          )}
        </li>

        <li><Link to="/profile">🙍 Trang cá nhân</Link></li>
      </ul>
    </aside>
  );
}
