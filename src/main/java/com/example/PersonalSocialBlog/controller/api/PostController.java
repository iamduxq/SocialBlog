package com.example.PersonalSocialBlog.controller.api;

import com.example.PersonalSocialBlog.dto.PostDTO;
import com.example.PersonalSocialBlog.entity.Enum.Visibility;
import com.example.PersonalSocialBlog.service.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PostController {
    private final IPostsService postsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDTO> createPost(@RequestParam("content") String content,
                                              @RequestParam("visibility")Visibility visibility,
                                              @RequestParam(value = "image", required = false) MultipartFile image) {
        PostDTO newPost = postsService.createPost(content, visibility, image);
        return ResponseEntity.ok(newPost);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PostDTO> getPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(postsService.getPostBySlug(slug));
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll() {
        return ResponseEntity.ok(postsService.findAll());
    }
}
