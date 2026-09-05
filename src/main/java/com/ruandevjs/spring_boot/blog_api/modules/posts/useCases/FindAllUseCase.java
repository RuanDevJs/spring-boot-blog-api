package com.ruandevjs.spring_boot.blog_api.modules.posts.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.posts.PostEntity;
import com.ruandevjs.spring_boot.blog_api.modules.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("findAllPostsUseCase")
public class FindAllUseCase {
    @Autowired
    private PostRepository postRepository;

    public List<PostEntity> execute(String order, String querySearch) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (order != null && order.equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(direction, "createdAt");

        if (querySearch != null && !querySearch.isBlank()) {
            return this.postRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    querySearch,
                    querySearch,
                    sort
            );
        }

        return this.postRepository.findAll(sort);
    }
}
