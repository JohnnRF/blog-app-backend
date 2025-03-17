package com.blogbackend.auth.service;

import com.blogbackend.auth.persistence.entity.Role;
import com.blogbackend.auth.persistence.entity.UserEntity;
import com.blogbackend.auth.persistence.repository.RoleRepository;
import com.blogbackend.auth.persistence.repository.UserRepository;
import com.blogbackend.auth.service.dto.UserDto;
import com.blogbackend.auth.service.dto.UserRequestDto;
import com.blogbackend.auth.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapp;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapp) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapp = userMapp;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
    }

    public UserEntity createUser (UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setNombres(userDto.getNombres());
        userEntity.setApellidos(userDto.getApellidos());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());

        Role defaultRole = roleRepository.findByName("USER");
        if(defaultRole != null){
            userEntity.getRoles().add(defaultRole); // se agrega un rol predeterminado
        }else {
            throw new RuntimeException("Rol predeterminado no econtrado");
        }

        return userRepository.save(userEntity);
    }

    public UserDto updateUser(Long userId, UserRequestDto userRequest) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // Actualizar los campos del usuario
        userEntity.setApellidos(userRequest.getApellidos());
        userEntity.setNombres(userRequest.getNombres());
        userEntity.setUsername(userRequest.getUsername());
        // Solo actualizar la contraseña si no está vacía
        if (userRequest.getPassword() != null && !userRequest.getPassword().isBlank()) {
            userEntity.setPassword(userRequest.getPassword());
        }

        // 3️⃣ Actualizar roles si se enviaron nuevos rolesIds
        if (userRequest.getRolesIds() != null) {
            Set<Role> newRoles = new HashSet<>(roleRepository.findAllById(userRequest.getRolesIds()));
            userEntity.setRoles(newRoles);
        }

        UserEntity userEntityUpdated = userRepository.save(userEntity);
        return userMapp.toDto(userEntityUpdated);
    }

    public void deleteUser(Long userId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // Eliminar relaciones en la tabla intermedia
        userEntity.getRoles().clear();
        // Eliminar el usuario de la base de datos
        userRepository.delete(userEntity);
    }
}
