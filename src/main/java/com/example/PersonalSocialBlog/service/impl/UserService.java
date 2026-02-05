package com.example.PersonalSocialBlog.service.impl;

import com.example.PersonalSocialBlog.component.ServiceHelper;
import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.dto.authDTO.RegisterRequest;
import com.example.PersonalSocialBlog.entity.Enum.AuthProvider;
import com.example.PersonalSocialBlog.entity.RoleEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import com.example.PersonalSocialBlog.mapper.UserMapper;
import com.example.PersonalSocialBlog.security.config.SecurityConfig;
import com.example.PersonalSocialBlog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        user.setProvider(AuthProvider.LOCAL);
        UserEntity savedUser = service.userRepository.save(user);
        userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getCurrentUser(String printcipal) {
//        UserEntity user = service.userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        UserEntity user = service.userRepository.findByEmail(printcipal)
                .or(() -> service.userRepository.findByUsername(printcipal))
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Không xác thực");
        }
        String identifier = auth.getName();
        return service.userRepository.findByEmail(identifier).or(
                () -> service.userRepository.findByUsername(identifier)).orElseThrow(
                () -> new RuntimeException("Người dùng không tồn tại!")
        );
    }
}