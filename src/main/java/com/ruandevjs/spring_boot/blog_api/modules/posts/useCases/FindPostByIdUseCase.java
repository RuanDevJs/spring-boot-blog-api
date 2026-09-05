package com.ruandevjs.spring_boot.blog_api.modules.posts.useCases;

import com.ruandevjs.spring_boot.blog_api.exceptions.posts.PostNotFoundException;
import com.ruandevjs.spring_boot.blog_api.modules.posts.PostEntity;
import com.ruandevjs.spring_boot.blog_api.modules.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindPostByIdUseCase {
    @Autowired
    private PostRepository postRepository;

    public Optional<PostEntity> execute(UUID postId) {
        Optional<PostEntity> postEntity = this.postRepository.findById(postId);
        if (postEntity.isPresent()) return postEntity;

        throw new PostNotFoundException();
    }
}
