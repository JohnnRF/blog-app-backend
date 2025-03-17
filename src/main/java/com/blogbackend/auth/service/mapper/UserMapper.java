package com.blogbackend.auth.service.mapper;

import com.blogbackend.auth.persistence.entity.UserEntity;
import com.blogbackend.auth.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserEntity userEntity){
        UserDto dto = new UserDto();
        dto.setApellidos(userEntity.getApellidos());
        dto.setNombres(userEntity.getNombres());
        dto.setUsername(userEntity.getUsername());
        dto.setPassword(userEntity.getPassword());

        return dto;
    }
}
