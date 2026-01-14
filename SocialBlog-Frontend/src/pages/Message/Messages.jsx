export default function Messages() {
  return (
    <div className="flex h-[75vh] bg-white border rounded overflow-hidden">
      
      {/* Danh sách hội thoại */}
      <div className="w-1/3 border-r">
        <h2 className="p-3 font-bold border-b">Tin nhắn</h2>

        <div className="p-3 hover:bg-gray-100 cursor-pointer">
          <p className="font-semibold">Trần Văn B</p>
          <p className="text-sm text-gray-500">Chào bạn...</p>
        </div>

        <div className="p-3 hover:bg-gray-100 cursor-pointer">
          <p className="font-semibold">Lê Văn C</p>
          <p className="text-sm text-gray-500">Tối rảnh không?</p>
        </div>
      </div>

      {/* Nội dung chat */}
      <div className="flex-1 flex flex-col">
        <div className="p-3 border-b font-semibold">
          Trần Văn B
        </div>

        {/* Nội dung tin nhắn */}
        <div className="flex-1 p-3 space-y-2 overflow-y-auto">
          <div className="bg-gray-200 p-2 rounded w-fit">
            Chào bạn
          </div>

          <div className="bg-blue-600 text-white p-2 rounded w-fit ml-auto">
            Chào, có chuyện gì không?
          </div>
        </div>

        {/* Ô nhập */}
        <div className="p-3 border-t flex">
          <input
            className="flex-1 border rounded px-3 py-2"
            placeholder="Nhập tin nhắn..."
          />
          <button className="ml-2 bg-blue-600 text-white px-4 rounded">
            Gửi
          </button>
        </div>
      </div>

    </div>
  );
}
