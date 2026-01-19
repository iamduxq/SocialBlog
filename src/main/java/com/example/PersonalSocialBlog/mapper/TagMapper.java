package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.TagDTO;
import com.example.PersonalSocialBlog.entity.TagEntity;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagDTO toDTO(TagEntity entity) {
        if (entity == null) return null;

        TagDTO dto = new TagDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }
}
