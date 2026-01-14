import { createContext, useContext, useState } from "react";

const NotificationContext = createContext();

export function NotificationProvider({ children }) {
  const [notifications, setNotifications] = useState([
    {
      id: 1,
      type: "like",
      postId: 101,
      message: "Trần Văn B đã thích bài viết của bạn",
      isRead: false,
    },
    {
      id: 2,
      type: "comment",
      postId: 101,
      message: "Lê Văn C đã bình luận bài viết của bạn",
      isRead: false,
    },
  ]);

  const addNotification = (notification) => {
    setNotifications((prev) => [
      { ...notification, isRead: false },
      ...prev,
    ]);
  };

  const markAsRead = (id) => {
    setNotifications((prev) =>
      prev.map((n) =>
        n.id === id ? { ...n, isRead: true } : n
      )
    );
  };

  const unreadCount = notifications.filter(n => !n.isRead).length;

  return (
    <NotificationContext.Provider
      value={{ notifications, addNotification, markAsRead, unreadCount }}
    >
      {children}
    </NotificationContext.Provider>
  );
}

export const useNotification = () => useContext(NotificationContext);
