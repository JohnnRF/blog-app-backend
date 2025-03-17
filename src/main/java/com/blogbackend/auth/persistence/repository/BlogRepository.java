package com.blogbackend.auth.persistence.repository;

import com.blogbackend.auth.persistence.entity.Blog;
import com.blogbackend.auth.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByPublicoTrue(); // listas posts públicos
    List<Blog> findByUser(UserEntity userEntity); // listar posts de un usuario específico
}
