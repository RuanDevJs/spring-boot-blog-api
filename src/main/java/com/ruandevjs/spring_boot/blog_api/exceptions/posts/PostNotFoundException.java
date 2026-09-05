package com.ruandevjs.spring_boot.blog_api.exceptions.posts;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post inválido ou não existe!");
    }
}
