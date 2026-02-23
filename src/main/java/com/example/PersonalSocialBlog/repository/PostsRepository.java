package com.example.PersonalSocialBlog.repository;

import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import com.example.PersonalSocialBlog.entity.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
    List<PostsEntity> findAllByVisibilityOrderByCreatedDateDesc(Visibility visibility);
    boolean existsBySlug(String slug);
    Optional<PostsEntity> findBySlug(String slug);
    List<PostsEntity> findAllByOrderByCreatedDateDesc();

}
