package com.ruandevjs.spring_boot.blog_api.modules.posts;

import com.ruandevjs.spring_boot.blog_api.modules.users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "posts")
public class PostEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo 'title' não pode ser nulo!")
    @Length(min = 5)
    private String title;

    @NotBlank(message = "O campo 'description' não pode ser nulo!")
    @Length(min = 5)
    private String description;

    @CreationTimestamp()
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity userEntity;

    @Column(name = "user_id")
    @NotNull
    private UUID userId;
}
