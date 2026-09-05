package com.ruandevjs.spring_boot.blog_api.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordHandler {

    public String hashPassword(String defaultPassword){
        return BCrypt.hashpw(defaultPassword, BCrypt.gensalt());
    }

}
