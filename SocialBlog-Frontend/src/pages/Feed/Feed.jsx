import { useEffect, useState } from "react";
import postApi from "../../api/postApi";
import PostItem from "../../components/PostItem";
export default function Feed() {

  const [posts, setPosts] = useState([]);
  const [content, setContent] = useState("");
  const [loading, setLoading] = useState(false);
  const [imageFile, setImageFile] = useState(null);
  const [preview, setPreview] = useState(null);

  useEffect(() => {
    postApi.getAllPosts().then((res) => {
      setPosts(res.data);
    })
  }, []);

  const handleCreatePost = async () => {
    if (!content.trim()) return;
    setLoading(true);
    try {
      const formData = new FormData();
      formData.append("content", content);
      formData.append("visibility", "PUBLIC");
      if (imageFile) {
        formData.append("image", imageFile);
      }
      const res = await postApi.createPost(formData);
      const newPost = res.data;
      setPosts((prev) => [newPost, ...prev]);
      setContent("");
      setImageFile(null);
      setPreview(null);
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="space-y-6">
      {/* Đăng bài */}
      <div className="p-4 bg-white border rounded-lg shadow-sm">
        <textarea
          className="w-full p-3 border rounded-lg resize-none focus:outline-none focus:ring-1 focus:ring-blue-500"
          rows={3}
          placeholder="Bạn đang nghĩ gì?"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />

        {preview && (
          <div className="relative mt-3">
            <img
              src={preview}
              alt="preview"
              className="object-cover w-full rounded-lg max-h-80"
            />
            <button
              onClick={() => {
                setImageFile(null);
                setPreview(null);
              }}
              className="absolute px-2 text-white rounded-full top-2 right-2 bg-black/60"
            >
              ✕
            </button>
          </div>
        )}

        {/* Action bar */}
        <div className="flex items-center justify-between mt-3">
          {/* Upload ảnh */}
          <label className="flex items-center gap-2 px-3 py-1 text-blue-600 border rounded-lg cursor-pointer hover:bg-blue-50">
            🖼️ Ảnh
            <input
              type="file"
              accept="image/*"
              hidden
              onChange={(e) => {
                const file = e.target.files[0];
                if (!file) return;

                if (file.size > 5 * 1024 * 1024) {
                  alert("Ảnh tối đa 5MB thôi nhé 😅");
                  return;
                }

                setImageFile(file);
                setPreview(URL.createObjectURL(file));
              }}
            />
          </label>

          {/* Đăng bài */}
          <button
            onClick={handleCreatePost}
            disabled={loading || (!content.trim() && !imageFile)}
            className="px-5 py-2 font-semibold text-white bg-blue-600 rounded-lg disabled:opacity-50"
          >
            {loading ? "Đang đăng..." : "Đăng bài"}
          </button>
        </div>
      </div>


      {/* Danh sách bài viết */}
      {posts.map((post) => (
        <PostItem key={post.id} post={post} />
      ))}
    </div>
  );
}
