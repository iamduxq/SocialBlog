package com.example.PersonalSocialBlog.controller.api;

import com.example.PersonalSocialBlog.dto.UserDTO;
import com.example.PersonalSocialBlog.entity.Enum.FriendRelationStatus;
import com.example.PersonalSocialBlog.entity.Enum.FriendRequestAction;
import com.example.PersonalSocialBlog.service.IFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/friends")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FriendController {
    private final IFriendService friendService;

    // Gửi lời mời kết bạn
    @PostMapping("/request/{receiverId}")
    public ResponseEntity<?> sendRequest(@PathVariable Long receiverId, Principal principal) {
        friendService.sendFriendRequest(principal.getName(), receiverId);
        return ResponseEntity.ok("Đã gửi lời mời kết bạn");
    }

    //

    // Xử lý yêu cầu
    @PutMapping("/{id}")
    public ResponseEntity<?> handleRequest(@PathVariable Long id,
                                           @RequestParam FriendRequestAction action,
                                           Principal principal) {
        friendService.handleFriendRequest(id, principal.getName(), action);
        return ResponseEntity.ok("Thành công");
    }

    // Hủy kết bạn
    @DeleteMapping("/unfriend/{userId}")
    public ResponseEntity<?> unfriend(@PathVariable Long userId,
                                      Principal principal) {
        friendService.unfriend(userId, principal.getName());
        return ResponseEntity.ok("Đã hủy kết bạn");
    }

    // Danh sách bạn bè
    @GetMapping("/list")
    public ResponseEntity<?> getFriends(Principal principal) {
        return ResponseEntity.ok(friendService.getFriends(principal.getName()));
    }

    // Danh sách được yêu cầu kết bạn
    @GetMapping("/pending")
    public ResponseEntity<List<UserDTO>> getPending(Principal principal) {
        return ResponseEntity.ok(friendService.getPendingRequests(principal.getName()));
    }

    // Danh sách đã gửi yêu cầu kết bạn
    @GetMapping("/sent")
    public ResponseEntity<List<UserDTO>> getSent(Principal principal) {
        return ResponseEntity.ok(friendService.getSentRequests(principal.getName()));
    }

    // Trạng thái bạn bè
    @GetMapping("/status/{targetUserId}")
    public ResponseEntity<?> getStatus(@PathVariable Long targetUserId, Principal principal) {
        FriendRelationStatus status = friendService.getRelationStatus(principal.getName(), targetUserId);
        return ResponseEntity.ok(status);
    }
}
