package com.example.PersonalSocialBlog.service;

import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.entity.Enum.FriendRelationStatus;
import com.example.PersonalSocialBlog.entity.Enum.FriendRequestAction;
import com.example.PersonalSocialBlog.entity.FriendEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;

import java.util.List;

public interface IFriendService {
    void sendFriendRequest(String senderUsername, Long receiverId); // Gừi lời mời kết bạn
    void handleFriendRequest(Long requestId, String username, FriendRequestAction action);
    void unfriend(Long userId, String username); // Hủy kết bạn
    List<UserDTO> getFriends(String username); // Danh sách bạn bè
    List<FriendEntity> getPendingRequests(String username); // Danh sách gừi lời mời
    boolean isFriend(UserEntity user1, UserEntity user2); // Kiểm tra có là bạn bè không
    FriendRelationStatus getRelationStatus(String username, Long targetUserId);
}
