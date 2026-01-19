package com.example.PersonalSocialBlog.service.impl;

import com.example.PersonalSocialBlog.component.ServiceHelper;
import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.mapper.PostMapper;
import com.example.PersonalSocialBlog.repository.PostsRepository;
import com.example.PersonalSocialBlog.service.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService {
    private final ServiceHelper serviceHelper;
    private final PostMapper postMapper;

    @Override
    public List<PostDTO> findAll() {
        return postMapper.toDTOList(serviceHelper.postsRepository.findAll());
    }
}
