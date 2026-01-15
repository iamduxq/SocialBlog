package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.CommentDTO;
import com.example.PersonalSocialBlog.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public static CommentDTO toDTO(CommentEntity entity) {
        if (entity == null) return null;
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setUser(UserMapper.toDTO(entity.getUser()));
        dto.setPostId(entity.getPost() == null ? null : entity.getPost().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }
}
