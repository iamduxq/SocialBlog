import { useState } from "react";
import { useNotification } from "../context/NotificationContext";
import axiosClient from "../api/axiosClient";
import { useAuth } from "../context/AuthContext";
import postApi from "../api/postApi";

function formatTime(createdDate) {
  if (!createdDate) return "Vừa xong";
  const created = new Date(createdDate);
  const now = new Date();
  const diffMs = now - created;

  const diffMinutes = Math.floor(diffMs / (1000 * 60));
  const diffHours = Math.floor(diffMs / (1000 * 60 * 60));
  const diffDays = Math.floor(diffMs / (1000 * 60 * 60 * 24));

  if (diffMinutes < 1) return "Vừa xong";
  if (diffMinutes < 60) return `${diffMinutes} phút trước`;
  if (diffHours < 24) return `${diffHours} giờ trước`;
  if (diffDays < 7) return `${diffDays} ngày trước`;
  const d = created.getDate().toString().padStart(2, "0");
  const m = (created.getMonth() + 1).toString().padStart(2, "0");
  const y = created.getFullYear();
  return `${d}-${m}-${y}`;
}


export default function PostItem({ post }) {
  const { addNotification } = useNotification();
  const [likeCount, setLikeCount] = useState(post.likeCount || 0);
  const [liked, setLiked] = useState(false);
  const [comments, setComments] = useState(post.comments || []);
  const [commentText, setCommentText] = useState("");
  const { user } = useAuth();
  const [showMenu, setShowMenu] = useState(false);
  const isOwner = user?.username === post.user?.username;
  const [visibility, setVisibility] = useState(post.visibility);

  const handleLike = () => {
    setLiked(!liked);
    setLikeCount((prev) => (liked ? prev - 1 : prev + 1));
    addNotification({
      id: Date.now(),
      type: "like",
      postId: post.id,
      message: "Ai đó đã thích bài viết của bạn",
    });
  };

  const handleComment = () => {
    if (!commentText.trim()) return;
    const newComment = {
      id: Date.now(),
      user: "Bạn",
      content: commentText,
    };
    setComments([...comments, newComment]);
    setCommentText("");
    addNotification({
      id: Date.now(),
      type: "comment",
      postId: post.id,
      message: "Ai đó đã bình luận bài viết của bạn",
    });
  };

  const handleCopyLink = () => {
    const url = `${window.location.origin}/?post=${post.slug}`;
    navigator.clipboard.writeText(url);
    setShowMenu(false);
  }

  const handleVisibilityChange = async (newVisibility) => {
    try {
      await postApi.updateVisibility(post.id, {
        visibility: newVisibility,
      });
      setVisibility(newVisibility);
      setShowMenu(false);
    } catch (error) {
      console.error("Faile", error);
    }
  }

  const handleDelete = async () => {
    if (!window.confirm("Bạn có chắc muốn xóa bài này?")) return;
    await postApi.deletePost(post.id);
    window.location.reload();
  }

  return (
    <div className="p-4 space-y-3 bg-white border rounded scroll-mt-3" id={`post-${post.slug}`}>
      {/* Header bài viết */}
      <div className="flex items-start justify-between">
        <div className="flex items-center space-x-3">
          <img
            src={post.user?.avatar || "/default-avatar.png"}
            alt="avatar"
            className="object-cover w-10 h-10 rounded-full"
          />
          <div>
            <p className="font-semibold">{post.user?.fullName || "Người dùng"}</p>
            <p className="text-xs text-gray-500">
              {formatTime(post.createdDate)} {" "}
              <span className="mx-1">•</span>
              {visibility === "PUBLIC" && "🌍"}
              {visibility === "FRIENDS" && "👥"}
              {visibility === "PRIVATE" && "🔒"}
            </p>
          </div>
        </div>
        <div className="relative">
          <button
            onClick={() => setShowMenu(!showMenu)}
            className="px-2 text-xl rounded hover:bg-gray-100"
          >
            ...
          </button>
          {showMenu && (
            <div
              className="absolute right-0 z-20 mt-2 bg-white border rounded-lg shadow-md w-44"
              onClick={(e) => e.stopPropagation()}
            >
              {/* Chỉnh sửa quyền riêng tư */}
              {isOwner && (
                <>
                  <div className="px-4 py-2 text-xs text-gray-400">
                    Quyền riêng tư
                  </div>

                  <button
                    onClick={() => handleVisibilityChange("PUBLIC")}
                    className="w-full px-4 py-2 text-left hover:bg-gray-100"
                  >
                    {visibility === "PUBLIC" && "✓ "}
                    🌍 Công khai
                  </button>

                  <button
                    onClick={() => handleVisibilityChange("FRIENDS")}
                    className="w-full px-4 py-2 text-left hover:bg-gray-100"
                  >
                    {visibility === "FRIENDS" && "✓ "}
                    👥 Bạn bè
                  </button>

                  <button
                    onClick={() => handleVisibilityChange("PRIVATE")}
                    className="w-full px-4 py-2 text-left hover:bg-gray-100"
                  >
                    {visibility === "PRIVATE" && "✓ "}
                    🔒 Riêng tư
                  </button>

                  <div className="my-1 border-t"></div>
                </>
              )}
              {/* Xóa bài viết */}
              {isOwner && (
                <button
                  onClick={handleDelete}
                  className="w-full px-4 py-2 text-left hover:bg-gray-100"
                >
                  Xóa
                </button>
              )}

              {/* Sao chép liên kết */}
              <button
                onClick={handleCopyLink}
                className="w-full px-4 py-2 text-left hover:bg-gray-100"
              >
                Sao chép liên kết
              </button>
            </div>
          )}
        </div>
      </div>

      {/* Nội dung */}
      <p>{post.content}</p>
      {post.imageUrls && post.imageUrls.length > 0 && (
        <div className="grid grid-cols-2 gap-2 mt-2">
          {post.imageUrls.map((url, index) => (
            <img
              key={index}
              src={`${axiosClient.defaults.baseURL}/${url}`}
              alt="post"
              className="object-cover w-full rounded max-h-96"
            />
          ))}
        </div>
      )}


      {/* Like & Comment count */}
      <div className="flex justify-between text-sm text-gray-600">
        <span className="cursor-pointer hover:underline hover:underline-offset-2">{likeCount} lượt thích</span>
        <span className="cursor-pointer hover:underline hover:underline-offset-2">{comments.length} bình luận</span>
      </div>

      {/* Action */}
      <div className="flex pt-2 text-sm border-t">
        <button
          onClick={handleLike}
          className={`flex-1 py-2 ${liked ? "text-white font-semibold bg-blue-700 rounded-lg" : ""
            }`}
        >
          👍 Thích
        </button>

        <button className="flex-1 py-2">
          💬 Bình luận
        </button>
      </div>

      {/* Comment list */}
      <div className="space-y-2">
        {comments.map((c) => (
          <div key={c.id} className="text-sm">
            <span className="font-semibold">{c.user}:</span>{" "}
            <span>{c.content}</span>
          </div>
        ))}
      </div>

      {/* Input comment */}
      <div className="flex space-x-2">
        <input
          value={commentText}
          onChange={(e) => setCommentText(e.target.value)}
          className="flex-1 px-3 py-1 text-sm border rounded"
          placeholder="Viết bình luận..."
        />
        <button
          onClick={handleComment}
          className="px-3 text-sm text-white bg-blue-600 rounded"
        >
          Gửi
        </button>
      </div>
    </div>
  );
}