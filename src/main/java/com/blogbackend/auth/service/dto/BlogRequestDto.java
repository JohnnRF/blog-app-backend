package com.blogbackend.auth.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestDto {
    private String titulo;
    private String contenido;
    private Boolean esPublico;
}
