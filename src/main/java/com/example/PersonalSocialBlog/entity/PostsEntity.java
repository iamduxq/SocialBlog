package com.example.PersonalSocialBlog.entity;

import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
public class PostsEntity extends BaseEntity {
    @Column(name= "title")
    private String title;

    @Column(name= "content", columnDefinition = "LONGTEXT")
    private String content;

    @Column(name= "description")
    private String description;

    @Column
    private String slug;
    private int viewCount = 0;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @Column(name = "imageUrl", nullable = true)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_user"))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_category"))
    private CategoryEntity category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostImage> images;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_post")),
            inverseJoinColumns = @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "fk_tag"))
    )
    private List<TagEntity> tags;
}
