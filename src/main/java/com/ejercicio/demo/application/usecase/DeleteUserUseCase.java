package com.ejercicio.demo.application.usecase;

import com.ejercicio.demo.domain.port.UserRepositoryPort;

public class DeleteUserUseCase {
    private final UserRepositoryPort repository;

    public DeleteUserUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(Integer id) {
        repository.deleteById(id);
    }
}
