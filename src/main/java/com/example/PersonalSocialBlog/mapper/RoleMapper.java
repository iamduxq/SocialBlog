package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.RoleDTO;
import com.example.PersonalSocialBlog.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDTO toDTO(RoleEntity entity) {
        if (entity == null) return null;

        RoleDTO dto = new RoleDTO();
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());

        return dto;
    }


    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        return entity;
    }
}
