package com.ruandevjs.spring_boot.blog_api.modules.users.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.users.UserEntity;
import com.ruandevjs.spring_boot.blog_api.modules.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteOneById {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;

    public void execute(UUID userId){
        findUserByIdUseCase.execute(userId);
        this.userRepository.deleteById(userId);
    }
}
