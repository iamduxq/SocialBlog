// import { useEffect, useState } from "react";
// import { getPosts } from "../../service/postService";
import PostItem from "../../components/PostItem";

export default function Feed() {
  return (
    <div className="space-y-6">
      {/* Đăng bài */}
      <div className="p-4 bg-white border rounded">
        <textarea
          className="w-full p-2 border rounded"
          placeholder="Bạn đang nghĩ gì?"
        />
        <button className="px-4 py-2 mt-2 text-white bg-blue-600 rounded">
          Đăng bài
        </button>
      </div>

      {/* Danh sách bài viết */}
      <PostItem />
      <PostItem />
    </div>
  );
}
