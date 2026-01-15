package com.example.PersonalSocialBlog.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest {
    private Long receiverId;
    private String content;
}
