package com.example.PersonalSocialBlog.service.impl;

import com.example.PersonalSocialBlog.component.ServiceHelper;
import com.example.PersonalSocialBlog.component.SlugUtils;
import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import com.example.PersonalSocialBlog.entity.PostsEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import com.example.PersonalSocialBlog.mapper.PostMapper;
import com.example.PersonalSocialBlog.service.FileService;
import com.example.PersonalSocialBlog.service.IPostsService;
import com.example.PersonalSocialBlog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService {
    private final ServiceHelper serviceHelper;
    private final IUserService userService;
    private final PostMapper postMapper;
    private final FileService fileService;

    @Override
    public List<PostDTO> findAll() {
        List<PostsEntity> posts = serviceHelper.postsRepository
                .findAllByVisibilityOrderByCreatedDateDesc(Visibility.PUBLIC);
        return postMapper.toDTOList(posts);
    }

    @Override
    public PostDTO createPost(String content, Visibility visibility, MultipartFile image) {
        String shortContent = content.length() > 50 ? content.substring(0, 50) : content;
        String baseSlug = SlugUtils.toSlug(shortContent);
        String slug = baseSlug;
        int count = 1;
        while (serviceHelper.postsRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + count++;
        }
        UserEntity currentUser = userService.getCurrentUser();
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            if (image.getSize() > 10 * 1024 * 1024) {
                throw new RuntimeException("Ảnh vượt quá 10MB");
            } else imageUrl = fileService.saveImg(image);
        }
        PostsEntity post = new PostsEntity();
        post.setUser(currentUser);
        post.setSlug(slug);
        post.setContent(content);
        post.setVisibility(visibility);
        post.setViewCount(0);
        post.setImageUrl(imageUrl);
        PostsEntity newPost = serviceHelper.postsRepository.save(post);
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
}
