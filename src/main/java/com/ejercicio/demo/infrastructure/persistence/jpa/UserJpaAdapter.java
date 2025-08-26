package com.ejercicio.demo.infrastructure.persistence.jpa;

import com.ejercicio.demo.domain.model.User;
import com.ejercicio.demo.domain.port.UserRepositoryPort;
import com.ejercicio.demo.infrastructure.mapper.UserMapper;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserJpaAdapter implements UserRepositoryPort {

    private final UsuarioRepository jpaRepository;
    private final UserMapper mapper;

    public UserJpaAdapter(UsuarioRepository jpaRepository, UserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Integer id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var entity = jpaRepository.findByEmail(email);
        return entity != null ? Optional.of(mapper.toDomain(entity)) : Optional.empty();
    }

    @Override
    public User save(User user) {
        Usuario entity = mapper.toEntity(user);
        Usuario saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }
}
