package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.dto.UserProfileDTO;
import com.example.PersonalSocialBlog.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Map to UserDTO
    public static UserDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setUsername(userEntity.getUsername());
        dto.setFullName(userEntity.getFullName());
        dto.setEmail(userEntity.getEmail());
        dto.setAvatar(userEntity.getAvatar());
        dto.setRole(RoleMapper.toDTO(userEntity.getRoles()));
        dto.setCreatedDate(userEntity.getCreatedDate());
        dto.setCreatedBy(userEntity.getCreatedBy());
        dto.setModifiedDate(userEntity.getModifiedDate());
        dto.setModifiedBy(userEntity.getModifiedBy());
        return dto;
    }

    public static UserProfileDTO toProfileDTO(UserEntity userEntity) {
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
}
