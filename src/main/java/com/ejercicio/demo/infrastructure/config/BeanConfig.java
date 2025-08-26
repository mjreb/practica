package com.ejercicio.demo.infrastructure.config;

import com.ejercicio.demo.application.usecase.*;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import com.ejercicio.demo.domain.port.UserRepositoryPort;
import com.ejercicio.demo.infrastructure.mapper.UserMapper;
import com.ejercicio.demo.infrastructure.persistence.jpa.UserJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.BiPredicate;

@Configuration
public class BeanConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UsuarioRepository repo, UserMapper mapper) {
        return new UserJpaAdapter(repo, mapper);
    }

    // Simplified password matcher: delegate to repository via email and compare in service layer if needed
    @Bean
    public BiPredicate<String, String> passwordMatcher() {
        // In a real impl, inject a PasswordEncoder and the repository to fetch hash by email
        return (email, raw) -> true; // Placeholder: accept any; keep controllers/old service for actual validation if needed
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort port) {
        return new CreateUserUseCase(port);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepositoryPort port) {
        return new UpdateUserUseCase(port);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepositoryPort port) {
        return new DeleteUserUseCase(port);
    }

    @Bean
    public ListUsersUseCase listUsersUseCase(UserRepositoryPort port) {
        return new ListUsersUseCase(port);
    }

    @Bean
    public GetUserUseCase getUserUseCase(UserRepositoryPort port) {
        return new GetUserUseCase(port);
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepositoryPort port, BiPredicate<String, String> matcher) {
        return new LoginUseCase(port, matcher);
    }
}
