package com.ruandevjs.spring_boot.blog_api.modules.posts.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.posts.PostEntity;
import com.ruandevjs.spring_boot.blog_api.modules.posts.PostRepository;
import com.ruandevjs.spring_boot.blog_api.modules.users.useCases.FindUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindPostsByUserIdUseCase {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;

    public List<PostEntity> execute(UUID userId) {
        this.findUserByIdUseCase.execute(userId);
        return this.postRepository.findByUserId(userId);
    }
}
