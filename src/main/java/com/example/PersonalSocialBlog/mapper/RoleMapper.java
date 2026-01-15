package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.RoleDTO;
import com.example.PersonalSocialBlog.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public static RoleDTO toDTO(RoleEntity entity) {
        if (entity == null) return null;

        RoleDTO dto = new RoleDTO();
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());

        return dto;
    }
}
