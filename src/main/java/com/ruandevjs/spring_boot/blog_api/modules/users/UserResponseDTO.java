package com.ruandevjs.spring_boot.blog_api.modules.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDTO{
    private UUID id;
    private String username;
    private String email;

    @JsonFormat(pattern = "dd 'de' MMMM 'às' HH:mm", locale = "pt-BR",timezone = "America/Sao_Paulo")
    private LocalDateTime createdAt;
}
