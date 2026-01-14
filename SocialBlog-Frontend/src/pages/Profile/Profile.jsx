import PostItem from "../../components/PostItem";

export default function Profile() {
  return (
    <div className="space-y-6">
      <div className="bg-white border rounded p-6 flex space-x-4">
        <div className="w-20 h-20 bg-gray-300 rounded-full" />
        <div>
          <h2 className="text-xl font-bold">Nguyễn Văn A</h2>
          <p className="text-gray-600">demo@gmail.com</p>
        </div>
      </div>

      <h3 className="font-bold">Bài viết của bạn</h3>
      <PostItem />
    </div>
  );
}
