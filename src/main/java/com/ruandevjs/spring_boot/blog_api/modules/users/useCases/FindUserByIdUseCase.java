package com.ruandevjs.spring_boot.blog_api.modules.users.useCases;

import com.ruandevjs.spring_boot.blog_api.exceptions.users.UserNotFoundException;
import com.ruandevjs.spring_boot.blog_api.modules.users.UserEntity;
import com.ruandevjs.spring_boot.blog_api.modules.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindUserByIdUseCase {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> execute(UUID userId){
        Optional<UserEntity> userEntity = this.userRepository.findById(userId);
        if(userEntity.isPresent()) return userEntity;

        throw new UserNotFoundException();
    }
}
