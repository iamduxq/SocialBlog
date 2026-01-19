package com.example.PersonalSocialBlog.service;

import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.dto.authDTO.RegisterRequest;

public interface IUserService {
    UserDTO loginUser(UserDTO user);
    void register(RegisterRequest request);
    UserDTO getCurrentUser(String username);
}