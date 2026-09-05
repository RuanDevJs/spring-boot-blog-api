package com.ruandevjs.spring_boot.blog_api.modules.users.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.users.UserEntity;
import com.ruandevjs.spring_boot.blog_api.modules.users.UserRepository;
import com.ruandevjs.spring_boot.blog_api.utils.PasswordHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateOneByIdUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;
    @Autowired
    PasswordHandler passwordHandler;

    public void execute(UUID userId, Optional<UserEntity> payload){
        Optional<UserEntity> userEntity = findUserByIdUseCase.execute(userId);
        userEntity.ifPresent((user) -> {
            if(!user.getUsername().isEmpty()) user.setUsername(payload.get().getUsername());
            if(!user.getEmail().isEmpty()) user.setEmail(payload.get().getEmail());
            if(!user.getPassword().isEmpty()){
                String hashedPassword = passwordHandler.hashPassword(payload.get().getPassword());
                user.setPassword(hashedPassword);
            }

            this.userRepository.save(user);
        });
    }
}
