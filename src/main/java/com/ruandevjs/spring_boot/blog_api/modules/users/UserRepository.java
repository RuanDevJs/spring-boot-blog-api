package com.ruandevjs.spring_boot.blog_api.modules.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    public Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
