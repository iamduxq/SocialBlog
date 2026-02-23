package com.example.PersonalSocialBlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends AbstractDTO<UserDTO> {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String avatar;
    private RoleDTO role;
    private Long friendRequestId;
}
