package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.dto.PostDetailDTO;
import com.example.PersonalSocialBlog.entity.PostsEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostMapper {
    public static PostDTO toDTO(PostsEntity entity) {
        if (entity == null) return null;

        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setSlug(entity.getSlug());
        dto.setViewCount(entity.getViewCount());
        dto.setVisibility(entity.getVisibility());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUser(UserMapper.toDTO(entity.getUser()));
        dto.setCategory(CategoryMapper.toDTO(entity.getCategory()));

        dto.setCommentCount(
                entity.getComments() == null ? 0 : entity.getComments().size()
        );
        dto.setTags(
                entity.getTags() == null ? null :
                        entity.getTags()
                                .stream()
                                .map(TagMapper::toDTO)
                                .collect(Collectors.toList())
        );
        return dto;
    }

    public static PostDetailDTO toDetailDTO(PostsEntity entity) {
        if (entity == null) return null;

        PostDetailDTO dto = new PostDetailDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        dto.setSlug(entity.getSlug());
        dto.setViewCount(entity.getViewCount());
        dto.setVisibility(entity.getVisibility());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUser(UserMapper.toDTO(entity.getUser()));
        dto.setCategory(CategoryMapper.toDTO(entity.getCategory()));

        dto.setTags(
                entity.getTags() == null ? null :
                        entity.getTags()
                                .stream()
                                .map(TagMapper::toDTO)
                                .collect(Collectors.toList())
        );
        dto.setComments(
                entity.getComments() == null ? null :
                        entity.getComments()
                                .stream()
                                .map(CommentMapper::toDTO)
                                .collect(Collectors.toList())
        );
        return dto;
    }
}
