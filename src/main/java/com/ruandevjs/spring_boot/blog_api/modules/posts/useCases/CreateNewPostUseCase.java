package com.ruandevjs.spring_boot.blog_api.modules.posts.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.posts.PostEntity;
import com.ruandevjs.spring_boot.blog_api.modules.posts.PostRepository;
import com.ruandevjs.spring_boot.blog_api.modules.users.useCases.FindUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNewPostUseCase {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;

    public PostEntity execute(PostEntity payload) {
        this.findUserByIdUseCase.execute(payload.getUserId());
        return this.postRepository.save(payload);
    }
}
