package com.example.PersonalSocialBlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends AbstractDTO<CategoryDTO> {
    private String name;
    private String code;
    private Integer postCount;
}
