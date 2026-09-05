package com.ruandevjs.spring_boot.blog_api.modules.posts;

import com.ruandevjs.spring_boot.blog_api.modules.posts.useCases.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<PostResponseDTO> findAll(
            @RequestParam(required = false, defaultValue = "DESC") String order,
            @RequestParam(required = false) String querySearch,
            // PROBLEMA: o número da página não é validado. Valores negativos chegam ao
            // PageRequest e geram uma IllegalArgumentException, que pode resultar em HTTP 500.
            // Valide o parâmetro com @Min(0) e trate erros de validação como HTTP 400.
            @RequestParam(required = false, defaultValue = "0") int page
    ) {
        Page<PostEntity> postEntityPage = this.findAllUseCase.execute(order, querySearch, page);

        return postEntityPage.map(postEntity ->
            new PostResponseDTO(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getDescription(),
                    postEntity.getCreatedAt()
            )
        );
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
    // PROBLEMA: receber a entidade JPA diretamente permite que o cliente envie campos que
    // deveriam ser controlados pelo servidor, como id, userId e createdAt. Isso possibilita
    // falsificar a autoria e pode fazer save() atualizar um registro existente quando um id
    // já cadastrado for informado. Use um DTO de criação sem esses campos e monte uma nova entidade.
    public PostEntity create(@RequestBody @Valid PostEntity payload) {
        return this.createNewPostUseCase.execute(payload);
    }

    @PutMapping("/{postId}")
    // PROBLEMA: Optional<PostEntity> não representa adequadamente um corpo de requisição e
    // não possui @Valid. Dados que violam as restrições da entidade podem chegar à persistência
    // e gerar uma exceção não tratada (HTTP 500). Use um DTO de atualização validado.
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
