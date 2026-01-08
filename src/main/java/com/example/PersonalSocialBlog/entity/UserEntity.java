package com.example.PersonalSocialBlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity extends BaseEntity{

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    // Constrait to other entity in database
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostsEntity> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentEntity> comment;

    // Friendship Relative
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<FriendEntity> senderFriendRequests;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<FriendEntity> receiverFriendRequests;

    // Message Relative
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<MessageEntity> sentMessage;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<MessageEntity> receivedMessage;

    // Role
    @ManyToOne
    @JoinColumn(name = "roles_id", foreignKey = @ForeignKey(name = "fk_user_role"))
    private RoleEntity roles;
}
