package com.example.PersonalSocialBlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdDate")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "modifiedDate")
    @CreationTimestamp
    private LocalDateTime modifiedDate;

    @Column(name = "modifiedBy")
    private String modifiedBy;

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
