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
        // PROBLEMA: verificar apenas se o userId informado existe não confirma que ele pertence
        // ao usuário autenticado. Como o valor vem do cliente, qualquer pessoa pode publicar em
        // nome de outro usuário. Obtenha o usuário da autenticação e ignore userId enviado no corpo.
        this.findUserByIdUseCase.execute(payload.getUserId());

        // PROBLEMA: save() recebe a entidade completa fornecida pelo cliente. Se payload.id
        // contiver o UUID de um post existente, o JPA pode executar um merge e sobrescrever esse
        // registro em vez de criar outro. Crie uma entidade nova e não copie id do DTO de entrada.
        return this.postRepository.save(payload);
    }
}
