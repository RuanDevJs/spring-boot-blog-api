package com.ruandevjs.spring_boot.blog_api.modules.users.useCases;

import com.ruandevjs.spring_boot.blog_api.exceptions.users.UserFoundException;
import com.ruandevjs.spring_boot.blog_api.modules.users.UserEntity;
import com.ruandevjs.spring_boot.blog_api.modules.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNewUserUseCase {
    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(UserEntity payload){
        this.userRepository
                .findByUsernameOrEmail(payload.getUsername(), payload.getEmail())
                .ifPresent((user) -> { throw new UserFoundException(); });
        return this.userRepository.save(payload);
    }
}
