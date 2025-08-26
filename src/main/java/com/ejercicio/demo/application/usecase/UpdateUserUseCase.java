package com.ejercicio.demo.application.usecase;

import com.ejercicio.demo.domain.model.User;
import com.ejercicio.demo.domain.port.UserRepositoryPort;

public class UpdateUserUseCase {
    private final UserRepositoryPort repository;

    public UpdateUserUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(User user) {
        return repository.save(user);
    }
}
