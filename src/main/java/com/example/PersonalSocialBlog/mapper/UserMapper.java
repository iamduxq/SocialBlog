package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.dto.UserProfileDTO;
import com.example.PersonalSocialBlog.dto.authDTO.RegisterRequest;
import com.example.PersonalSocialBlog.entity.RoleEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleMapper roleMapper;

    // Map to UserDTO
    public UserDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setUsername(userEntity.getUsername());
        dto.setFullName(userEntity.getFullName());
        dto.setEmail(userEntity.getEmail());
        dto.setAvatar(userEntity.getAvatar());
        dto.setRole(roleMapper.toDTO(userEntity.getRoles()));
        dto.setCreatedDate(userEntity.getCreatedDate());
        dto.setCreatedBy(userEntity.getCreatedBy());
        dto.setModifiedDate(userEntity.getModifiedDate());
        dto.setModifiedBy(userEntity.getModifiedBy());
        return dto;
    }

    // Map to UserEntity
    public UserEntity ToEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setFullName(dto.getFullName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());
        if (dto.getRole() != null) {
            entity.setRoles(roleMapper.toEntity(dto.getRole()));
        }
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        return entity;
    }

    public UserProfileDTO toProfileDTO(UserEntity userEntity) {
        if (userEntity == null) return null;
        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(userEntity.getId());
        dto.setUsername(userEntity.getUsername());
        dto.setEmail(userEntity.getEmail());
        dto.setFullName(userEntity.getFullName());
        dto.setAvatar(userEntity.getAvatar());
        dto.setCreatedDate(userEntity.getCreatedDate());
        dto.setCreatedBy(userEntity.getCreatedBy());
        dto.setModifiedDate(userEntity.getModifiedDate());
        dto.setModifiedBy(userEntity.getModifiedBy());

        dto.setTotalPosts(userEntity.getPosts() == null ? 0 : userEntity.getPosts().size());
        int sender = userEntity.getSenderFriendRequests() == null ? 0 : userEntity.getSenderFriendRequests().size();
        int receiver = userEntity.getReceiverFriendRequests() == null ? 0 : userEntity.getReceiverFriendRequests().size();
        dto.setTotalFriends(sender + receiver);
        return dto;
    }

    // Register
    public UserEntity registerRequest(RegisterRequest req, RoleEntity role) {
        if (req == null) return null;
        UserEntity user = new UserEntity();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setAvatar(req.getAvatar());
        user.setRoles(role);
        return user;
    }
}
