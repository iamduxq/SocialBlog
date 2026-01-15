package com.example.PersonalSocialBlog.dto;

import com.example.PersonalSocialBlog.entity.Enum.FriendStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendDTO extends AbstractDTO<FriendDTO> {
    private UserDTO friend;
    private FriendStatus status;
    private Boolean isSender;
}
