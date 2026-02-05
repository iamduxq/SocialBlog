package com.example.PersonalSocialBlog.service;

import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostsService {
    List<PostDTO> findAll();
    PostDTO createPost(String content, Visibility visibility, MultipartFile image);
    PostDTO getPostBySlug(String slug);
}
