package com.example.PersonalSocialBlog.controller.api;

import com.example.PersonalSocialBlog.component.ServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PostController {
    private final ServiceHelper serviceHelper;

//    @GetMapping("/")
//    public List
}
