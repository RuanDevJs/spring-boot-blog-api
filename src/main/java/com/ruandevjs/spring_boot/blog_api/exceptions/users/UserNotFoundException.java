package com.ruandevjs.spring_boot.blog_api.exceptions.users;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuário inválido ou não existe!");
    }
}
