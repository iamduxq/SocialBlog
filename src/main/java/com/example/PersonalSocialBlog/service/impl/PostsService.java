package com.example.PersonalSocialBlog.service.impl;

import com.example.PersonalSocialBlog.component.ServiceHelper;
import com.example.PersonalSocialBlog.component.SlugUtils;
import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.entity.Enum.FriendStatus;
import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import com.example.PersonalSocialBlog.entity.PostImage;
import com.example.PersonalSocialBlog.entity.PostsEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import com.example.PersonalSocialBlog.mapper.PostMapper;
import com.example.PersonalSocialBlog.service.IFriendService;
import com.example.PersonalSocialBlog.service.IPostsService;
import com.example.PersonalSocialBlog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService {
    private final ServiceHelper serviceHelper;
    private final IUserService userService;
    private final PostMapper postMapper;
    private final IFriendService friendService;

    @Override
    public List<PostDTO> findAll(String loginValue) {
        UserEntity curr = serviceHelper.userRepository
                .findByUsername(loginValue)
                .or(() -> serviceHelper.userRepository.findByEmail(loginValue))
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        List<PostsEntity> posts = serviceHelper.postsRepository
                .findAllByOrderByCreatedDateDesc();
        List<PostsEntity> filtered = posts.stream().filter(post -> canViewPost(post, curr)).toList();
        return postMapper.toDTOList(filtered);
    }

    @Override
    public PostDTO createPost(String content, Visibility visibility, List<MultipartFile> images) {
        String shortContent = content.length() > 50 ? content.substring(0, 50) : content;
        String baseSlug = SlugUtils.toSlug(shortContent);
        String slug = baseSlug;
        int count = 1;
        while (serviceHelper.postsRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + count++;
        }
        UserEntity currentUser = userService.getCurrentUser();
        PostsEntity post = new PostsEntity();
        post.setUser(currentUser);
        post.setSlug(slug);
        post.setContent(content);
        post.setVisibility(visibility);
        post.setViewCount(0);
        PostsEntity newPost = serviceHelper.postsRepository.save(post);
        if (images != null && !images.isEmpty()) {
            for (MultipartFile file : images) {
                if (file.isEmpty()) continue;
                if (file.getSize() > 10 * 1024 * 1024) {
                    throw new RuntimeException("Ảnh vượt quá 10MB");
                }
                try {
                    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path uploadPath = Paths.get("uploads");
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    Files.copy(
                            file.getInputStream(),
                            uploadPath.resolve(fileName),
                            StandardCopyOption.REPLACE_EXISTING
                    );
                    PostImage postImage = new PostImage();
                    postImage.setPost(newPost);
                    postImage.setImageUrl("uploads/" + fileName);
                    serviceHelper.postImageRepository.save(postImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return postMapper.toDTO(newPost);
    }

    @Override
    public PostDTO getPostBySlug(String slug) {
        PostsEntity post = serviceHelper.postsRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
        post.setViewCount(post.getViewCount() + 1);
        serviceHelper.postsRepository.save(post);
        return postMapper.toDTO(post);
    }

    @Override
    public void deletePost(Long id) {
        PostsEntity post = serviceHelper.postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));

        UserEntity curr = userService.getCurrentUser();
        if (!post.getUser().getId().equals(curr.getId())) {
            throw new RuntimeException("Bạn không ó quyền xóa bài viết này");
        }
        serviceHelper.postsRepository.delete(post);
    }

    @Override
    public void updateVisibility(Long postId, Visibility visibility, String username) {
        PostsEntity post = serviceHelper.postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
        post.setVisibility(visibility);
        serviceHelper.postsRepository.save(post);
    }

    private boolean canViewPost(PostsEntity posts, UserEntity curr) {
        UserEntity owner = posts.getUser();
        // PRIVATE
        if ((posts.getUser().getId().equals(curr.getId()))) {
            return true;
        }
        // PUBLIC
        if (posts.getVisibility() == Visibility.PUBLIC) {
            return true;
        }
        // FRIENDS
        if (posts.getVisibility() == Visibility.FRIENDS && friendService.isFriend(curr, owner)) {
            return true;
        }
        return false;
    }
}
