package com.ejercicio.demo.application.usecase;

import com.ejercicio.demo.domain.model.User;
import com.ejercicio.demo.domain.port.UserRepositoryPort;

import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public class LoginUseCase {
    private final UserRepositoryPort repository;
    private final BiPredicate<String, String> passwordMatcher;

    public LoginUseCase(UserRepositoryPort repository, BiPredicate<String, String> passwordMatcher) {
        this.repository = repository;
        this.passwordMatcher = passwordMatcher;
    }

    public User execute(String email, String rawPassword) {
        var user = repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Invalid credentials"));
        // In this simple implementation, password comparison happens in the adapter via matcher
        if (!passwordMatcher.test(email, rawPassword)) {
            throw new NoSuchElementException("Invalid credentials");
        }
        return user;
    }
}
