package com.example.PersonalSocialBlog.service;

import com.example.PersonalSocialBlog.dto.PostDTO;

import java.util.List;

public interface IPostsService {
    List<PostDTO> findAll();
}
