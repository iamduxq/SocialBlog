package com.example.PersonalSocialBlog.service;

import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.dto.authDTO.RegisterRequest;
import com.example.PersonalSocialBlog.entity.UserEntity;

public interface IUserService {
    UserDTO loginUser(UserDTO user);
    void register(RegisterRequest request);
    UserDTO getCurrentUser(String username);
    UserEntity getCurrentUser();
}