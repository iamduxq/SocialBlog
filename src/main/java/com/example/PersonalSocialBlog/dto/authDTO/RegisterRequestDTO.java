package com.example.PersonalSocialBlog.dto.authDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String fullName;
}
