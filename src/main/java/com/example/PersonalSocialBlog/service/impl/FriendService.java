package com.example.PersonalSocialBlog.service.impl;

import com.example.PersonalSocialBlog.component.ServiceHelper;
import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.entity.Enum.FriendRelationStatus;
import com.example.PersonalSocialBlog.entity.Enum.FriendRequestAction;
import com.example.PersonalSocialBlog.entity.Enum.FriendStatus;
import com.example.PersonalSocialBlog.entity.FriendEntity;
import com.example.PersonalSocialBlog.entity.UserEntity;
import com.example.PersonalSocialBlog.mapper.UserMapper;
import com.example.PersonalSocialBlog.service.IFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService implements IFriendService {

    private final ServiceHelper serviceHelper;
    private final UserMapper userMapper;

    @Override
    public void sendFriendRequest(String senderUsername, Long receiverId) {
        UserEntity sender = serviceHelper.userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Không tim thấy người gửi"));
        UserEntity receiver = serviceHelper.userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Không tim thấy người nhận"));

        if (sender.getId().equals(receiver.getId())) {
            throw new RuntimeException("Không thể kết bạn với chính mình");
        }

        if (isFriend(sender, receiver)) {
            throw new RuntimeException("Hai người đã là bạn bè");
        }

        boolean requestExists = serviceHelper.friendRepository.existsBySenderAndReceiver(sender, receiver)
                || serviceHelper.friendRepository.existsBySenderAndReceiver(receiver, sender);

        if (requestExists) {
            throw new RuntimeException("Lời mời đã tồn tại");
        }
        FriendEntity friend = new FriendEntity();
        friend.setCreatedBy(senderUsername);
        friend.setSender(sender);
        friend.setReceiver(receiver);
        friend.setStatus(FriendStatus.PENDING);
        serviceHelper.friendRepository.save(friend);
    }

    @Override
    public void handleFriendRequest(Long requestId, String username, FriendRequestAction action) {
        UserEntity currentUser = serviceHelper.userRepository.findByUsername(username).orElseThrow();
        FriendEntity request = serviceHelper.friendRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lời mời"));
        switch (action) {
            // Chấp nhận lời mời
            case ACCEPT -> {
                if (!request.getReceiver().getId().equals(currentUser.getId())) {
                    throw new RuntimeException("Không có quyền");
                }
                request.setStatus(FriendStatus.ACCEPTED);
                serviceHelper.friendRepository.save(request);
            }
            // Từ chối lời mời
            case REJECT -> {
                if (!request.getReceiver().getId().equals(currentUser.getId())) {
                    throw new RuntimeException("Không có quyền");
                }
                serviceHelper.friendRepository.delete(request);
            }
            // Hủy yêu cầu gửi lời mời kết bạn
            case CANCEL -> {
                if (!request.getSender().getId().equals(currentUser.getId())) {
                    throw new RuntimeException("Không có quyền");
                }
                serviceHelper.friendRepository.delete(request);
            }
        }
    }

    @Override
    public void unfriend(Long userId, String username) {
        UserEntity currentUser = serviceHelper.userRepository.findByUsername(username).orElseThrow();
        UserEntity targetUser = serviceHelper.userRepository.findById(userId).orElseThrow();
        Optional<FriendEntity> friendship = serviceHelper.friendRepository.findBySenderAndReceiver(currentUser, targetUser);
        if (friendship.isEmpty()) {
            friendship = serviceHelper.friendRepository.findBySenderAndReceiver(targetUser, currentUser);
        }
        friendship.ifPresent(serviceHelper.friendRepository::delete);
    }

    @Override
    public List<UserDTO> getFriends(String username) {
        UserEntity user = serviceHelper.userRepository.findByUsername(username).orElseThrow();
        List<FriendEntity> sent = serviceHelper.friendRepository.findBySenderAndStatus(user, FriendStatus.ACCEPTED);
        List<FriendEntity> receiver = serviceHelper.friendRepository.findByReceiverAndStatus(user, FriendStatus.ACCEPTED);
        List<UserDTO> friends = new ArrayList<>(); // Loi tu day
        sent.forEach(f -> friends.add(userMapper.toDTO(f.getReceiver())));
        receiver.forEach(f -> friends.add(userMapper.toDTO(f.getSender())));
        return friends;
    }

    @Override
    public List<FriendEntity> getPendingRequests(String username) {
        UserEntity user = serviceHelper.userRepository.findByUsername(username).orElseThrow();
        return serviceHelper.friendRepository.findByReceiverAndStatus(user, FriendStatus.PENDING);
    }

    @Override
    public boolean isFriend(UserEntity user1, UserEntity user2) {
        if (user1.getId().equals(user2.getId())) {
            return false;
        }

        return serviceHelper.friendRepository.existsBySenderAndReceiverAndStatus(
                user1, user2, FriendStatus.ACCEPTED
        ) || serviceHelper.friendRepository.existsBySenderAndReceiverAndStatus(
                user2, user1, FriendStatus.ACCEPTED
        );
    }

    @Override
    public FriendRelationStatus getRelationStatus(String username, Long targetUserId) {
        UserEntity currentUser = serviceHelper.userRepository.findByUsername(username).orElseThrow();
        UserEntity targetUser = serviceHelper.userRepository.findById(targetUserId).orElseThrow();

        if (currentUser.getId().equals(targetUser.getId())) {
            return FriendRelationStatus.NONE;
        }
        // Kiểm tra là bạn bè không
        if (isFriend(currentUser, targetUser)) {
            return FriendRelationStatus.FRIENDS;
        }
        // Kiểm tra đã gửi lời mời chưa
        Optional<FriendEntity> sent = serviceHelper.friendRepository.findBySenderAndReceiver(currentUser, targetUser);
        if (sent.isPresent() && sent.get().getStatus() == FriendStatus.PENDING) {
            return FriendRelationStatus.REQUEST_SENT;
        }
        // Kiểm tra đã nhận lời mời chưa
        Optional<FriendEntity> received = serviceHelper.friendRepository.findBySenderAndReceiver(targetUser, currentUser);
        if (received.isPresent() && received.get().getStatus() == FriendStatus.PENDING) {
            return FriendRelationStatus.REQUEST_RECEIVED;
        }
        return FriendRelationStatus.NONE;
    }
}
