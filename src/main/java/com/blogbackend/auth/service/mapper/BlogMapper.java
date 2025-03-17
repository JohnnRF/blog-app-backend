package com.blogbackend.auth.service.mapper;

import com.blogbackend.auth.persistence.entity.Blog;
import com.blogbackend.auth.service.dto.BlogDto;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {
    public BlogDto toDto(Blog blog){
        BlogDto dto = new BlogDto();
        dto.setId(blog.getId());
        dto.setTitulo(blog.getTitulo());
        dto.setContenido(blog.getContenido());
        dto.setEsPublico(blog.getPublico());
        dto.setFechaCreacion(blog.getFechaCreacion());
        dto.setAutor(blog.getUser().getNombres() + " " + blog.getUser().getApellidos());
        return dto;
    }
}
