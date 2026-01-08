package com.example.PersonalSocialBlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter @Setter
public class CommentEntity extends BaseEntity{
    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_comment_user"))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_comment_post"))
    private PostsEntity post;
}
