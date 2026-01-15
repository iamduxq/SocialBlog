package com.example.PersonalSocialBlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDTO extends AbstractDTO<TagDTO> {
    private String name;
    private String code;
}
