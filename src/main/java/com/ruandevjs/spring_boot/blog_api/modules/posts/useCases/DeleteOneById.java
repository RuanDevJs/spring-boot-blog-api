package com.ruandevjs.spring_boot.blog_api.modules.posts.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("deleteOnePostById")
public class DeleteOneById {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FindPostByIdUseCase findPostByIdUseCase;

    public void execute(UUID postId) {
        this.findPostByIdUseCase.execute(postId);
        this.postRepository.deleteById(postId);
    }
}
