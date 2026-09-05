package com.ruandevjs.spring_boot.blog_api.modules.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PostResponseDTO {
    private UUID id;
    private String title;
    private String description;

    @JsonFormat(pattern = "dd 'de' MMMM 'às' HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private LocalDateTime createdAt;
}
