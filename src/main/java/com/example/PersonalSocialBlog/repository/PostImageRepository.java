package com.example.PersonalSocialBlog.repository;

import com.example.PersonalSocialBlog.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
