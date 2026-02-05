package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.dto.PostDetailDTO;
import com.example.PersonalSocialBlog.dto.request.PostCreateRequest;
import com.example.PersonalSocialBlog.entity.PostsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;
    private final CommentMapper commentMapper;
    private final TagMapper tagMapper;

    public PostDTO toDTO(PostsEntity entity) {
        if (entity == null) return null;

        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setSlug(entity.getSlug());
        dto.setContent(entity.getContent());
        dto.setViewCount(entity.getViewCount());
        dto.setVisibility(entity.getVisibility());
        dto.setImageUrl(entity.getImageUrl());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUser(userMapper.toDTO(entity.getUser()));
        dto.setCategory(categoryMapper.toDTO(entity.getCategory()));

        dto.setCommentCount(
                entity.getComments() == null ? 0 : entity.getComments().size()
        );
        dto.setTags(
                entity.getTags() == null ? null :
                        entity.getTags()
                                .stream()
                                .map(tagMapper::toDTO)
                                .collect(Collectors.toList())
        );
        return dto;
    }

    public List<PostDTO> toDTOList(List<PostsEntity> entity) {
        return entity.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PostDetailDTO toDetailDTO(PostsEntity entity) {
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
        dto.setUser(userMapper.toDTO(entity.getUser()));
        dto.setCategory(categoryMapper.toDTO(entity.getCategory()));

        dto.setTags(
                entity.getTags() == null ? null :
                        entity.getTags()
                                .stream()
                                .map(tagMapper::toDTO)
                                .collect(Collectors.toList())
        );
        dto.setComments(
                entity.getComments() == null ? null :
                        entity.getComments()
                                .stream()
                                .map(commentMapper::toDTO)
                                .collect(Collectors.toList())
        );
        return dto;
    }


    public PostsEntity toEntity(PostDTO dto) {
        if (dto == null) return null;
        PostsEntity entity = new PostsEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setSlug(dto.getSlug());
        entity.setContent(dto.getContent());
        entity.setImageUrl(dto.getImageUrl());
        entity.setVisibility(dto.getVisibility());
        entity.setViewCount(dto.getViewCount());
//        entity.setUser(userMapper.ToEntity(dto.getUser()));
        return entity;
    }
}
