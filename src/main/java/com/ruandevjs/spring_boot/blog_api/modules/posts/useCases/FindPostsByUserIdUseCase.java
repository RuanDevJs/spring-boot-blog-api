package com.ruandevjs.spring_boot.blog_api.modules.posts.useCases;

import com.ruandevjs.spring_boot.blog_api.modules.posts.PostEntity;
import com.ruandevjs.spring_boot.blog_api.modules.posts.PostRepository;
import com.ruandevjs.spring_boot.blog_api.modules.users.useCases.FindUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindPostsByUserIdUseCase {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;

    public List<PostEntity> execute(UUID userId) {
        this.findUserByIdUseCase.execute(userId);

        // PROBLEMA: a consulta retorna todos os posts do usuário sem paginação. Uma conta com
        // muitos posts pode causar consulta lenta, resposta grande e consumo excessivo de memória.
        // Receba um Pageable e retorne Page<PostEntity>, mantendo o limite de itens por página.
        return this.postRepository.findByUserId(userId);
    }
}
