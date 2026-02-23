package com.example.PersonalSocialBlog.component;

import com.example.PersonalSocialBlog.repository.*;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper {
    public final CategoryRepository categoryRepository;
    public final CommentRepository commentRepository;
    public final FriendRepository friendRepository;
    public final MessageRepository messageRepository;
    public final PostsRepository postsRepository;
    public final RoleRepository roleRepository;
    public final UserRepository userRepository;
    public final PostImageRepository postImageRepository;

    public ServiceHelper(CategoryRepository categoryRepository, CommentRepository commentRepository, FriendRepository friendRepository, MessageRepository messageRepository, PostsRepository postsRepository, RoleRepository roleRepository, UserRepository userRepository, PostImageRepository postImageRepository) {
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
        this.friendRepository = friendRepository;
        this.messageRepository = messageRepository;
        this.postsRepository = postsRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.postImageRepository = postImageRepository;
    }
}
