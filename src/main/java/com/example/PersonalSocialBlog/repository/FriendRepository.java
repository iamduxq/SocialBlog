package com.example.PersonalSocialBlog.repository;

import com.example.PersonalSocialBlog.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
}
