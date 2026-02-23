import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import postApi from "../api/postApi";

export default function PostDetail() {
  const { slug } = useParams();
  const [post, setPost] = useState(null);
  useEffect(() => {
    postApi.getPostBySlug(slug)
      .then(res => setPost(res.data))
      .catch(err => console.log(err));
  }, [slug]);

  if (!post) return <div>Loading...</div>;

  return (
    <div className="max-w-2xl p-4 mx-auto bg-white rounded shadow">

      {/* Nội dung */}
      <p className="mb-4 text-lg">{post.content}</p>

      {/* Ảnh */}
      {post.postImages && post.postImages.length > 0 && (
        <div className="space-y-2">
          {post.postImages.map((img) => (
            <img
              key={img.id}
              src={img.imageUrl}
              alt="post"
              className="object-cover w-full rounded"
            />
          ))}
        </div>
      )}

    </div>
  );
}