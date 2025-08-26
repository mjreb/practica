package com.ejercicio.demo.application.usecase;

import com.ejercicio.demo.domain.model.User;
import com.ejercicio.demo.domain.port.UserRepositoryPort;
import java.util.List;

public class ListUsersUseCase {
    private final UserRepositoryPort repository;

    public ListUsersUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public List<User> execute() {
        return repository.findAll();
    }
}
