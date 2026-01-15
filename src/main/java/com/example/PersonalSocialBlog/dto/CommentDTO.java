package com.example.PersonalSocialBlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO extends AbstractDTO<CommentDTO>{
    private String content;
    private UserDTO user;
    private Long postId;
}
