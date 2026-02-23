package com.example.PersonalSocialBlog.dto;

import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO extends AbstractDTO<PostDTO> {
    private String title;
    private String description;
    private String content;
    private String slug;
    private int viewCount;
    private Visibility visibility;
    private UserDTO user;
    private CategoryDTO category;
    private int commentCount;
    private List<TagDTO> tags;
    private List<String> imageUrls;
}
