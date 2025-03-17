package com.blogbackend.auth.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserRequestDto {
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private List<Long> rolesIds;
}
