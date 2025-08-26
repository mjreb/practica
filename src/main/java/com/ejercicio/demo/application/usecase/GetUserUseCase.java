package com.ejercicio.demo.application.usecase;

import com.ejercicio.demo.domain.model.User;
import com.ejercicio.demo.domain.port.UserRepositoryPort;

import java.util.NoSuchElementException;

public class GetUserUseCase {
    private final UserRepositoryPort repository;

    public GetUserUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
