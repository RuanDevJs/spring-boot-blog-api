package com.ruandevjs.spring_boot.blog_api.exceptions.users;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Nome de usuário ou E-mail já cadastrado na plafatorma!");
    }
}
