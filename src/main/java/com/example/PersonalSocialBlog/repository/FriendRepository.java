package com.example.PersonalSocialBlog.repository;

import com.example.PersonalSocialBlog.entity.Enum.FriendStatus;
import com.example.PersonalSocialBlog.entity.FriendEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    boolean existsBySenderAndReceiver(UserEntity sender, UserEntity receiver);
    boolean existsBySenderAndReceiverAndStatus(UserEntity sender, UserEntity receiver, FriendStatus status);
    List<FriendEntity> findByReceiverAndStatus(UserEntity receiver, FriendStatus status);
    List<FriendEntity> findBySenderAndStatus(UserEntity sender, FriendStatus status);
    Optional<FriendEntity> findBySenderAndReceiver(UserEntity sender, UserEntity receiver);
}
