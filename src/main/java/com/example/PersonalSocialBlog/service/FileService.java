package com.example.PersonalSocialBlog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    private static final String UPLOAD_DIR = "uploads/";
    public String saveImg(MultipartFile file) {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.write(filePath, file.getBytes());
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Upload ảnh thất bại!", e);
        }
    }
}
