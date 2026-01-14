import { useNavigate } from "react-router-dom";
import { useNotification } from "../../context/NotificationContext";

export default function Notifications() {
  const { notifications, markAsRead } = useNotification();
  const navigate = useNavigate();

  const handleClick = (n) => {
    markAsRead(n.id);
    navigate(`/post/${n.postId}`);
  };

  return (
    <div className="space-y-4">
      <h2 className="text-xl font-bold">Thông báo</h2>

      {notifications.map((n) => (
        <div
          key={n.id}
          onClick={() => handleClick(n)}
          className={`p-4 border rounded cursor-pointer ${
            n.isRead ? "bg-gray-100" : "bg-white"
          }`}
        >
          <p>{n.message}</p>
        </div>
      ))}
    </div>
  );
}
