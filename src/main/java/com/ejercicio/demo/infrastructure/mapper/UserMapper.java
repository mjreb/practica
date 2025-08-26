package com.ejercicio.demo.infrastructure.mapper;

import com.ejercicio.demo.domain.model.User;
import com.ejercicio.demo.capanegocio.modelo.Usuario;

public class UserMapper {

    public User toDomain(Usuario entity) {
        if (entity == null) return null;
        User d = new User();
        d.setId(entity.getIdUsuario());
        d.setName(entity.getNombre());
        d.setEmail(entity.getEmail());
        d.setRole(entity.getRol());
        return d;
    }

    public Usuario toEntity(User user) {
        if (user == null) return null;
        Usuario e = new Usuario();
        if (user.getId() != null) e.setIdUsuario(user.getId());
        e.setNombre(user.getName());
        e.setEmail(user.getEmail());
        e.setRol(user.getRole());
        // La contraseña se gestiona fuera del dominio; aquí no se setea
        return e;
    }
}
