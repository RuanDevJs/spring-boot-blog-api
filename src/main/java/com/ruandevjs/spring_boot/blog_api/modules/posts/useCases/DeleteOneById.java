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
        // PROBLEMA: confirmar apenas que o post existe não verifica se ele pertence ao usuário
        // autenticado. Qualquer pessoa que conheça o UUID pode excluir o registro.
        // Valide a propriedade do post ou exclua usando postId e o id do usuário autenticado.
        this.findPostByIdUseCase.execute(postId);
        this.postRepository.deleteById(postId);
    }
}
