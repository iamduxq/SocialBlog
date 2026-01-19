package com.example.PersonalSocialBlog.service.impl;

import com.example.PersonalSocialBlog.component.ServiceHelper;
import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.dto.authDTO.RegisterRequest;
import com.example.PersonalSocialBlog.entity.RoleEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import com.example.PersonalSocialBlog.mapper.UserMapper;
import com.example.PersonalSocialBlog.security.config.SecurityConfig;
import com.example.PersonalSocialBlog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final ServiceHelper service;
    private final SecurityConfig securityConfig;
    private final UserMapper userMapper;

    @Override
    public UserDTO loginUser(UserDTO dto) {
        List<UserEntity> user = service.userRepository.findFirstByUsername(dto.getUsername());
        if (user.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại");
        }
        UserEntity entity = user.get(0);


        if (!securityConfig.passwordEncoder().matches(dto.getPassword(), entity.getPassword())) {
            throw new RuntimeException("Mật khẩu không chính xác!");
        }
        return userMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        if (service.userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }
        RoleEntity role = service.roleRepository.findByCode("USER")
                .orElseThrow(() -> new RuntimeException("Role USER không tồn tại"));
        UserEntity user = userMapper.registerRequest(request, role);
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        UserEntity savedUser = service.userRepository.save(user);
        userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getCurrentUser(String username) {
        UserEntity user = service.userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return userMapper.toDTO(user);
    }
}