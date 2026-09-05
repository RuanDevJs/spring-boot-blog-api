package com.ruandevjs.spring_boot.blog_api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    public String field, message;
}
