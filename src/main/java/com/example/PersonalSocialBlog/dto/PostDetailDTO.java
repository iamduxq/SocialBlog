package com.example.PersonalSocialBlog.dto;

import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDetailDTO extends AbstractDTO<PostDetailDTO>{
    private String title;
    private String content;
    private String description;
    private String slug;
    private int viewCount;
    private Visibility visibility;
    private UserDTO user;
    private CategoryDTO category;
    private List<CommentDTO> comments;
    private List<TagDTO> tags;
}
