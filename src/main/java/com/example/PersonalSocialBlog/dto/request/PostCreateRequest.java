package com.example.PersonalSocialBlog.dto.request;

import java.util.List;

public class PostCreateRequest {
    private String title;
    private String content;
    private String description;
    private Long categoryId;
    private String image;
    private List<Long> tagIds;
}
