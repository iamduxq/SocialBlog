package com.example.PersonalSocialBlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tags")
@Getter @Setter
public class TagEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String code;

    @ManyToMany(mappedBy = "tags")
    private List<PostsEntity> posts;
}
