package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.MessageDTO;
import com.example.PersonalSocialBlog.entity.MessageEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserMapper userMapper;

    public MessageDTO toDTO(MessageEntity entity, UserEntity currentUser) {
        if (entity == null || currentUser == null) return null;
        MessageDTO dto = new MessageDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSender(userMapper.toDTO(entity.getSender()));
        dto.setReceiver(userMapper.toDTO(entity.getReceiver()));
        dto.setIsMine(entity.getSender().getId().equals(currentUser.getId()));
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }
}
