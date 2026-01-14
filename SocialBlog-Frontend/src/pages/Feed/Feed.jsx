import PostItem from "../../components/PostItem";

export default function Feed() {
  return (
    <div className="space-y-6">
      {/* Đăng bài */}
      <div className="bg-white border rounded p-4">
        <textarea
          className="w-full border rounded p-2"
          placeholder="Bạn đang nghĩ gì?"
        />
        <button className="mt-2 bg-blue-600 text-white px-4 py-2 rounded">
          Đăng bài
        </button>
      </div>

      {/* Danh sách bài viết */}
      <PostItem />
      <PostItem />
    </div>
  );
}
