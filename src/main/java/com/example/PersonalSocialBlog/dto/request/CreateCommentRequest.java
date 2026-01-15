package com.example.PersonalSocialBlog.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private Long postId;
    private String content;
}
