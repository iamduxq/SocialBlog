package com.example.PersonalSocialBlog.service;

import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostsService {
    List<PostDTO> findAll(String username);
    PostDTO createPost(String content, Visibility visibility, List<MultipartFile> images);
    PostDTO getPostBySlug(String slug);
    void deletePost(Long id);
    void updateVisibility(Long postId, Visibility visibility, String username);
}
