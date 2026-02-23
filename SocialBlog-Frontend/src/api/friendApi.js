import axiosClient from "./axiosClient";

const BASE_URL = "/api/friends";
const friendApi = {
    getFriends() {
        return axiosClient.get(`${BASE_URL}/list`);
    },
    // Status
    getStatus(targetUserId) {
        return axiosClient.get(`${BASE_URL}/status/${targetUserId}`);
    },
    // Gửi lời mời
    sendRequest(targetUserId) {
        return axiosClient.post(`${BASE_URL}/request/${targetUserId}`);
    },
    // Xử lý request (ACCEPT/REJECT/CANCEL)
    handleRequest(requestId, action) {
        return axiosClient.put(
            `${BASE_URL}/${requestId}`,
            null,
        {
            params: {action}
        });
    },
    // Hủy kết bạn
    unfriend(userId) {
        return axiosClient.delete(`${BASE_URL}/unfriend/${userId}`);
    },
    // Lời mời đã nhận
    getPending() {
        return axiosClient.get(`${BASE_URL}/pending`);
    },
    // Lời mời đã gửi
    getSent() {
        return axiosClient.get(`${BASE_URL}/sent`);
    },
    // Bạn bè
    getFriendCount() {
        return axiosClient.get(`${BASE_URL}/count`);
    }
};
export default friendApi;