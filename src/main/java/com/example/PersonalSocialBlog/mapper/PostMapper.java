package com.example.PersonalSocialBlog.mapper;

import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.dto.PostDetailDTO;
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
        dto.setViewCount(entity.getViewCount());
        dto.setVisibility(entity.getVisibility());
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
}
