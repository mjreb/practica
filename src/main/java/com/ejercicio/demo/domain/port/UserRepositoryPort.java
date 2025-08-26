package com.ejercicio.demo.domain.port;

import com.ejercicio.demo.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    List<User> findAll();
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    User save(User user);
    void deleteById(Integer id);
}
