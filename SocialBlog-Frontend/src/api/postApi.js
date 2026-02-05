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

}

export default postApi;