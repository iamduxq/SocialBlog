package com.example.PersonalSocialBlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "message")
@Getter @Setter
public class MessageEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name = "fk_message_user_sender"))
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name = "fk_message_user_receiver"))
    private UserEntity receiver;

    @Column(columnDefinition = "TEXT")
    private String content;
}
