package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.FriendDTO;
import com.example.PersonalSocialBlog.entity.FriendEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendMapper {

    private final UserMapper userMapper;

    public FriendDTO toDTO(FriendEntity entity, UserEntity currentUser) {
        if (entity == null || currentUser == null) return null;
        FriendDTO dto = new FriendDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        boolean isSender = entity.getSender().getId().equals(currentUser.getId());
        dto.setIsSender(isSender);

        if (isSender) {
            dto.setFriend(userMapper.toDTO(entity.getReceiver()));
        } else {
            dto.setFriend(userMapper.toDTO(entity.getSender()));
        }
        return dto;
    }
}
