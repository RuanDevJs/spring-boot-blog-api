package com.ruandevjs.spring_boot.blog_api.modules.posts;

import com.ruandevjs.spring_boot.blog_api.modules.posts.useCases.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private CreateNewPostUseCase createNewPostUseCase;

    @Autowired
    private FindAllUseCase findAllUseCase;

    @Autowired
    private FindPostsByUserIdUseCase findPostsByUserIdUseCase;

    @Autowired
    private UpdateOneByIdUseCase updateOneByIdUseCase;

    @Autowired
    private DeleteOneById deleteOneById;

    @GetMapping()
    public List<PostResponseDTO> findAll(
            @RequestParam(required = false, defaultValue = "DESC") String order,
            @RequestParam(required = false) String querySearch
    ) {
        List<PostEntity> postEntityList = this.findAllUseCase.execute(order, querySearch);
        List<PostResponseDTO> postResponseDTOS = new ArrayList<PostResponseDTO>();

        postEntityList.forEach(postEntity -> {
            PostResponseDTO postResponseDTO = new PostResponseDTO(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getDescription(),
                    postEntity.getCreatedAt()
            );
            postResponseDTOS.add(postResponseDTO);
        });

        return postResponseDTOS;
    }

    @GetMapping("/user/{userId}")
    public List<PostResponseDTO> findByUserId(@PathVariable UUID userId) {
        List<PostEntity> postEntityList = this.findPostsByUserIdUseCase.execute(userId);
        List<PostResponseDTO> postResponseDTOS = new ArrayList<PostResponseDTO>();

        postEntityList.forEach(postEntity -> {
            PostResponseDTO postResponseDTO = new PostResponseDTO(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getDescription(),
                    postEntity.getCreatedAt()
            );
            postResponseDTOS.add(postResponseDTO);
        });

        return postResponseDTOS;
    }

    @PostMapping()
    public PostEntity create(@RequestBody @Valid PostEntity payload) {
        return this.createNewPostUseCase.execute(payload);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Object> updateOneById(@PathVariable UUID postId, @RequestBody Optional<PostEntity> payload) {
        this.updateOneByIdUseCase.execute(postId, payload);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deleteOneById(@PathVariable UUID postId) {
        this.deleteOneById.execute(postId);
        return ResponseEntity.status(204).body(null);
    }
}
