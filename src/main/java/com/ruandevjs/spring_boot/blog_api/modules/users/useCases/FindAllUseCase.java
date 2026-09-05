package com.ruandevjs.spring_boot.blog_api.modules.users.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.users.UserEntity;
import com.ruandevjs.spring_boot.blog_api.modules.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUseCase {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> execute(){
        return this.userRepository.findAll();
    }
}
