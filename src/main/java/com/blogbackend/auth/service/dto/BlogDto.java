package com.blogbackend.auth.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogDto {
    private Long id;
    private String titulo;
    private String contenido;
    private Boolean esPublico;
    private LocalDateTime fechaCreacion;
    private String autor;

}
