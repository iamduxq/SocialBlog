import { useState } from "react";
import { useNotification } from "../context/NotificationContext";


export default function PostItem() {
  const [likeCount, setLikeCount] = useState(12);
  const [liked, setLiked] = useState(false);
  const { addNotification } = useNotification();


  const [comments, setComments] = useState([
    { id: 1, user: "Trần Văn B", content: "Bài viết hay quá!" },
    { id: 2, user: "Lê Văn C", content: "Chuẩn luôn 👍" },
  ]);

  const [commentText, setCommentText] = useState("");

  const handleLike = () => {
    setLiked(!liked);
    setLikeCount(liked ? likeCount - 1 : likeCount + 1);
    addNotification({
      id: Date.now(),
      type: "like",
      postId: 101,
      message: "Ai đó đã thích bài viết của bạn",
    });
  };

  const handleComment = () => {
    if (!commentText.trim()) return;

    setComments([
      ...comments,
      { id: Date.now(), user: "Bạn", content: commentText },
    ]);
    setCommentText("");
    addNotification({
      id: Date.now(),
      type: "comment",
      postId: 101,
      message: "Ai đó đã bình luận bài viết của bạn",
    });
  };

  return (
    <div className="p-4 space-y-3 bg-white border rounded">
      
      {/* Header bài viết */}
      <div className="flex items-center space-x-3">
        <div className="w-10 h-10 bg-gray-300 rounded-full" />
        <div>
          <p className="font-semibold">Nguyễn Văn A</p>
          <p className="text-xs text-gray-500">2 giờ trước</p>
        </div>
      </div>

      {/* Nội dung */}
      <p>
        Đây là nội dung bài viết demo cho SocialBlog. Phần này map từ bảng post.
      </p>

      {/* Like & Comment count */}
      <div className="flex justify-between text-sm text-gray-600">
        <span>{likeCount} lượt thích</span>
        <span>{comments.length} bình luận</span>
      </div>

      {/* Action */}
      <div className="flex pt-2 text-sm border-t">
        <button
          onClick={handleLike}
          className={`flex-1 py-2 ${
            liked ? "text-white font-semibold bg-blue-700 rounded-lg" : ""
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