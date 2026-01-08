package com.example.PersonalSocialBlog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter @Setter
public class RoleEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String code;

    @OneToMany(mappedBy = "roles")
    private List<UserEntity> user;
}
