package com.example.PersonalSocialBlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO extends AbstractDTO<UserProfileDTO> {
    private String username;
    private String email;
    private String fullName;
    private String avatar;
    private int totalPosts;
    private int totalFriends;
}
