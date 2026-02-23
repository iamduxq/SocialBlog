import { useEffect, useState } from "react";
import friendApi from "../../api/friendApi";

export default function Friends() {
  const [friends, setFriends] = useState([]);
  const [pending, setPending] = useState([]);
  const [sent, setSent] = useState([]);
  const [loading, setLoading] = useState(true);

  const [activeTab, setActiveTab] = useState("friends");

  const loadData = async () => {
    try {
      setLoading(true);

      const [friendsRes, pendingRes, sentRes] =
        await Promise.all([
          friendApi.getFriends(),
          friendApi.getPending(),
          friendApi.getSent()
        ]);

      setFriends(friendsRes.data);
      setPending(pendingRes.data);
      setSent(sentRes.data);

    } catch (err) {
      console.error("Lỗi khi tải dữ liệu:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  // Hủy kết bạn
  const handleUnfriend = async (userId) => {
    try {
      await friendApi.unfriend(userId);
      loadData();
    } catch (err) {
      console.error("Lỗi khi hủy kết bạn:", err);
    }
  };

  // Xử lý ACCEPT / REJECT / CANCEL
  const handleAction = async (requestId, action) => {
    try {
      await friendApi.handleRequest(requestId, action);
      loadData();
    } catch (err) {
      console.error("Lỗi xử lý lời mời:", err);
    }
  };

  if (loading) return <p>Đang tải...</p>;

  return (
    <div className="space-y-6">
      <h2 className="text-xl font-bold">Danh sách bạn bè</h2>

      {/* Tabs */}
      <div className="flex gap-6 pb-2 border-b">
        {["friends", "pending", "sent"].map((tab) => (
          <button
            key={tab}
            onClick={() => setActiveTab(tab)}
            className={`pb-2 capitalize ${
              activeTab === tab
                ? "border-b-2 border-blue-500 font-semibold"
                : "text-gray-500"
            }`}
          >
            {tab === "friends" && `Bạn bè (${friends.length})`}
            {tab === "pending" && `Lời mời kết bạn (${pending.length})`}
            {tab === "sent" && `Đã gửi lời mời (${sent.length})`}
          </button>
        ))}
      </div>

      {/* Content */}
      <div className="space-y-4">

        {/* FRIENDS */}
        {activeTab === "friends" && (
          <>
            {friends.length === 0 && (
              <p className="text-gray-500">Bạn chưa có bạn bè nào.</p>
            )}

            {friends.map((friend) => (
              <div
                key={friend.id}
                className="flex items-center justify-between p-4 bg-white border rounded"
              >
                <span>{friend.fullName || friend.username}</span>
                <button
                  onClick={() => handleUnfriend(friend.id)}
                  className="text-red-500 hover:text-red-700"
                >
                  Hủy kết bạn
                </button>
              </div>
            ))}
          </>
        )}

        {/* PENDING - người khác gửi cho mình */}
        {activeTab === "pending" && (
          <>
            {pending.length === 0 && (
              <p className="text-gray-500">Không có lời mời nào.</p>
            )}

            {pending.map((request) => (
              <div
                key={request.id}
                className="flex items-center justify-between p-4 bg-white border rounded"
              >
                <span>
                  {request.fullName || request.username}
                </span>

                <div className="flex gap-3">
                  <button
                    onClick={() => handleAction(request.friendRequestId, "ACCEPT")}
                    className="px-3 py-1 text-sm text-white bg-green-500 rounded hover:bg-green-600"
                  >
                    Chấp nhận
                  </button>

                  <button
                    onClick={() => handleAction(request.friendRequestId, "REJECT")}
                    className="px-3 py-1 text-sm text-white bg-red-500 rounded hover:bg-red-600"
                  >
                    Từ chối
                  </button>
                </div>
              </div>
            ))}
          </>
        )}

        {/* SENT - mình gửi cho người khác */}
        {activeTab === "sent" && (
          <>
            {sent.length === 0 && (
              <p className="text-gray-500">Bạn chưa gửi lời mời nào.</p>
            )}

            {sent.map((request) => (
              <div
                key={request.id}
                className="flex items-center justify-between p-4 bg-white border rounded"
              >
                <span>
                  {request.fullName || request.username}
                </span>

                <button
                  onClick={() => handleAction(request.friendRequestId, "CANCEL")}
                  className="px-3 py-1 text-sm text-white bg-gray-500 rounded hover:bg-gray-600"
                >
                  Hủy yêu cầu
                </button>
              </div>
            ))}
          </>
        )}

      </div>
    </div>
  );
}