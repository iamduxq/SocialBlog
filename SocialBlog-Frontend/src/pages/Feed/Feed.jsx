import { useEffect, useState, useRef } from "react";
import { useSearchParams } from "react-router-dom";
import postApi from "../../api/postApi";
import PostItem from "../../components/PostItem";

export default function Feed() {
  const [searchParam] = useSearchParams();
  const [posts, setPosts] = useState([]);
  const [content, setContent] = useState("");
  const [loading, setLoading] = useState(false);
  const [imageFile, setImageFile] = useState([]);
  const [preview, setPreview] = useState([]);
  const slug = searchParam.get("post");
  const hasScrolled = useRef(false);
  useEffect(() => {
    postApi.getAllPosts().then(res => {
      let data = res.data;

      if (slug) {
        const foundPost = data.find(p => p.slug === slug);

        if (foundPost) {
          data = [
            foundPost,
            ...data.filter(p => p.slug !== slug)
          ];
        }
      }

      setPosts(data);
    });
  }, [slug]);
  useEffect(() => {
    if (!slug || posts.length === 0 || hasScrolled.current) return;

    const element = document.getElementById(`post-${slug}`);
    if (element) {
      element.scrollIntoView({
        behavior: "smooth",
        block: "start",
      });

      // highlight nhẹ 2s
      element.classList.add("ring-2", "ring-blue-400");
      setTimeout(() => {
        element.classList.remove("ring-2", "ring-blue-400");
      }, 2000);

      hasScrolled.current = true;
    }
  }, [posts, slug]);


  const handleCreatePost = async () => {
    if (!content.trim() && imageFile.length === 0) return;
    setLoading(true);

    try {
      const formData = new FormData();
      formData.append("content", content);
      formData.append("visibility", "PUBLIC");
      imageFile.forEach((file) => {
        formData.append("images", file);
      });
      const res = await postApi.createPost(formData);
      const newPost = res.data;
      setPosts((prev) => [newPost, ...prev]);
      setContent("");
      setImageFile([]);
      setPreview([]);
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  const handleRemoveImage = (indexToRemove) => {
    setImageFile((prev) =>
      prev.filter((_, index) => index !== indexToRemove)
    );
    setPreview((prev) =>
      prev.filter((_, index) => index !== indexToRemove)
    );
  };

  return (
    <div className="space-y-6">
      <div className="p-4 bg-white border shadow-sm rounded-xl">
        <textarea
          className="w-full p-3 border rounded-lg resize-none focus:outline-none focus:ring-1 focus:ring-blue-500"
          rows={3}
          placeholder="Bạn đang nghĩ gì?"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />

        {preview.length > 0 && (
          <div className="flex flex-wrap gap-2 mt-3">
            {preview.map((src, index) => (
              <div key={index} className="relative w-[300px] h-[300px]">
                <img
                  src={src}
                  alt="preview"
                  className="object-cover w-full h-full border rounded-md"
                />
                <button
                  onClick={() => handleRemoveImage(index)}
                  className="absolute flex items-center justify-center w-4 h-4 text-xs text-white bg-black rounded-full -top-1 -right-1 hover:bg-red-500"
                >
                  ✕
                </button>
              </div>
            ))}
          </div>
        )}

        {/* Action bar */}
        <div className="flex items-center justify-between mt-3">
          <div className="flex gap-2">
            <label className="flex items-center gap-2 px-3 py-2 text-sm text-gray-600 rounded-lg cursor-pointer hover:bg-gray-100">
              🖼️ Ảnh
              <input
                type="file"
                accept="image/*"
                multiple
                hidden
                onChange={(e) => {
                  const files = Array.from(e.target.files);
                  if (!files.length) return;

                  const validFiles = files.filter(
                    (file) => file.size <= 10 * 1024 * 1024
                  );

                  if (validFiles.length !== files.length) {
                    alert("Mỗi ảnh tối đa 10MB thôi 😅");
                  }

                  setImageFile(validFiles);
                  setPreview(
                    validFiles.map((file) =>
                      URL.createObjectURL(file)
                    )
                  );
                }}
              />
            </label>
            <button className="px-3 py-2 text-sm text-gray-600 rounded-lg hover:bg-gray-100">
              😊 Cảm xúc
            </button>
          </div>

          <button
            onClick={handleCreatePost}
            disabled={loading || (!content.trim() && imageFile.length === 0)}
            className="px-5 py-2 text-sm font-semibold text-white bg-blue-600 rounded-lg hover:bg-blue-700 disabled:opacity-50"
          >
            {loading ? "Đang đăng..." : "Đăng bài"}
          </button>
        </div>
      </div>

      {/* Danh sách bài viết */}
      <div className="space-y-6">
        {posts.map((post) => (
          <PostItem key={post.id} post={post} />
        ))}
      </div>
    </div>
  );
}
