package com.example.PersonalSocialBlog.entity;

import com.example.PersonalSocialBlog.entity.Enum.FriendStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "friends")
@Getter @Setter
public class FriendEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name = "fk_friend_user_sender"))
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name = "fk_friend_user_receiver"))
    private UserEntity receiver;

    @Enumerated(EnumType.STRING)
    private FriendStatus status;
}