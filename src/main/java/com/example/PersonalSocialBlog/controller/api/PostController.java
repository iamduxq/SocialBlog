package com.example.PersonalSocialBlog.controller.api;

import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import com.example.PersonalSocialBlog.service.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PostController {
    private final IPostsService postsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDTO> createPost(@RequestParam("content") String content,
                                              @RequestParam("visibility")Visibility visibility,
                                              @RequestParam(value = "images", required = false) List<MultipartFile> images) {
        PostDTO newPost = postsService.createPost(content, visibility, images);
        return ResponseEntity.ok(newPost);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PostDTO> getPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(postsService.getPostBySlug(slug));
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll(Principal principal) {
        return ResponseEntity.ok(postsService.findAll(principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postsService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/visibility")
    public ResponseEntity<?> updateVisibility(@PathVariable Long id,
                                              @RequestBody Map<String, String> body,
                                              Principal principal) {
        String visibilityStr = body.get("visibility");
        Visibility visibility = Visibility.valueOf(visibilityStr);
        postsService.updateVisibility(id, visibility, principal.getName());
        return ResponseEntity.ok().build();
    }
}
