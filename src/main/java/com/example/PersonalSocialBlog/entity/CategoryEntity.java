package com.example.PersonalSocialBlog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "catagories")
@Getter @Setter
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String code;

    @OneToMany(mappedBy = "category")
    private List<PostsEntity> post;
}
