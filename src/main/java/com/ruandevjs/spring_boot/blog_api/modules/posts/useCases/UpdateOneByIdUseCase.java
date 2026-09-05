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
        // PROBLEMA: o post é localizado apenas pelo id, sem verificar se pertence ao usuário
        // autenticado. Assim, quem souber o UUID pode alterar posts de outras pessoas.
        // Busque pelo postId junto com o id do usuário autenticado ou valide a propriedade antes da alteração.
        Optional<PostEntity> postEntity = this.findPostByIdUseCase.execute(postId);
        postEntity.ifPresent((post) -> {
            if (payload.isEmpty()) return;

            PostEntity data = payload.get();
            // PROBLEMA: esta validação verifica somente se o texto está vazio e não aplica as
            // demais regras da entidade, como o mínimo de 5 caracteres. Um valor inválido pode
            // falhar apenas ao salvar e gerar HTTP 500. Valide um DTO antes de executar este caso de uso.
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
