export default function Friends() {
  return (
    <div className="space-y-4">
      <h2 className="text-xl font-bold">Danh sách bạn bè</h2>

      <div className="bg-white border rounded p-4 flex justify-between">
        <span>Trần Văn B</span>
        <button className="text-red-500">Hủy kết bạn</button>
      </div>

      <div className="bg-white border rounded p-4 flex justify-between">
        <span>Lê Văn C</span>
        <button className="text-red-500">Hủy kết bạn</button>
      </div>
    </div>
  );
}
