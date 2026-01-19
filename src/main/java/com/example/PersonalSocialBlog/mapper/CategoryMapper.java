package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.CategoryDTO;
import com.example.PersonalSocialBlog.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDTO toDTO(CategoryEntity entity) {
        if (entity == null) return null;
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setPostCount(
                entity.getPost() == null ? 0 : entity.getPost().size()
        );
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }
}
