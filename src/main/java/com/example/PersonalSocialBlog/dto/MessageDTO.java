package com.example.PersonalSocialBlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO extends AbstractDTO<MessageDTO> {
    private UserDTO sender;
    private UserDTO receiver;
    private String content;
    private Boolean isMine;
}
