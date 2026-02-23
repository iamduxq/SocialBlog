import axiosClient from "./axiosClient";

const BASE_URL = "/api/posts";
const postApi = {
    // Post public
    getAllPosts() {
        return axiosClient.get(BASE_URL);
    },

    // Post url sharing
    getPostBySlug(slug) {
        return axiosClient.get(`${BASE_URL}/${slug}`);
    },

    // Post
    createPost(formData) {
        return axiosClient.post(BASE_URL, formData, {
            headers: { "Content-Type": "multipart/form-data" }
        });
    },

    // Delete Post
    deletePost(id) {
        return axiosClient.delete(`${BASE_URL}/${id}`);
    },

    // Update visibility
    updateVisibility(postId, data) {
        return axiosClient.patch(`${BASE_URL}/${postId}/visibility`, data);
    }
}

export default postApi;