package com.blogbackend.auth.service;

import com.blogbackend.auth.persistence.entity.Blog;
import com.blogbackend.auth.persistence.entity.UserEntity;
import com.blogbackend.auth.persistence.repository.BlogRepository;
import com.blogbackend.auth.service.dto.BlogDto;
import com.blogbackend.auth.service.dto.BlogRequestDto;
import com.blogbackend.auth.service.mapper.BlogMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapp;

    public BlogService(BlogRepository blogRepository, BlogMapper blogMapp) {
        this.blogRepository = blogRepository;
        this.blogMapp = blogMapp;
    }

    public List<BlogDto> getPublics(){
        List<Blog> blogs = blogRepository.findByPublicoTrue();

        return  blogs.stream()
                .map(blog -> blogMapp.toDto(blog))
                .collect(Collectors.toList());
    }

    public BlogDto createBlog(BlogRequestDto blogRequest, UserEntity userEntity){
        Blog blog = new Blog();
        blog.setTitulo(blogRequest.getTitulo());
        blog.setContenido(blogRequest.getContenido());
        blog.setPublico(blogRequest.getEsPublico());
        blog.setFechaCreacion(LocalDateTime.now());
        blog.setUser(userEntity);

        Blog blogSaved = blogRepository.save(blog);

        return blogMapp.toDto(blogSaved);
    }

    public BlogDto updateBlog(Long blogId, BlogRequestDto blogRequest){
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog no encontrado"));

        // Actualizar los campos del blogs
        blog.setTitulo(blogRequest.getTitulo());
        blog.setContenido(blogRequest.getContenido());
        blog.setPublico(blogRequest.getEsPublico());

        // Guardar el blogs actualizado
        Blog blogUpdated = blogRepository.save(blog);

        // Retornar el DTO actualizado
        return blogMapp.toDto(blogUpdated);
    }

    public void deleteBlog (Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog no encontrado"));
        blogRepository.delete(blog);
    }
}
