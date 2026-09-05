package com.ruandevjs.spring_boot.blog_api.modules.users;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "users")
public class UserEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo 'username' não pode ser nulo!")
    @Length(min = 5)
    private String username;

    @NotBlank(message = "O campo 'password' não pode ser nulo!")
    @Length(min = 5)
    private String password;

    @Email(message = "Por favor, insira um e-mail válido!")
    @NotBlank(message = "O campo 'e-mail' não pode ser nulo!")
    private String email;

    @CreationTimestamp()
    private LocalDateTime createdAt;
}
