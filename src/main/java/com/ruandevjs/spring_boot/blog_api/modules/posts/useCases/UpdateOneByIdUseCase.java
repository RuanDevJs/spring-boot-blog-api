package com.ruandevjs.spring_boot.blog_api.modules.posts.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.posts.PostEntity;
import com.ruandevjs.spring_boot.blog_api.modules.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("updateOnePostByIdUseCase")
public class UpdateOneByIdUseCase {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FindPostByIdUseCase findPostByIdUseCase;

    public void execute(UUID postId, Optional<PostEntity> payload) {
        Optional<PostEntity> postEntity = this.findPostByIdUseCase.execute(postId);
        postEntity.ifPresent((post) -> {
            if (payload.isEmpty()) return;

            PostEntity data = payload.get();
            if (data.getTitle() != null && !data.getTitle().isEmpty()) {
                post.setTitle(data.getTitle());
            }
            if (data.getDescription() != null && !data.getDescription().isEmpty()) {
                post.setDescription(data.getDescription());
            }

            this.postRepository.save(post);
        });
    }
}
