package com.ejercicio.demo.infrastructure.api;

import com.ejercicio.demo.application.usecase.*;
import com.ejercicio.demo.domain.model.User;
import com.ejercicio.demo.infrastructure.api.dto.UserRequest;
import com.ejercicio.demo.infrastructure.api.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class UserController {

    private final CreateUserUseCase createUser;
    private final UpdateUserUseCase updateUser;
    private final DeleteUserUseCase deleteUser;
    private final ListUsersUseCase listUsers;
    private final GetUserUseCase getUser;

    public UserController(CreateUserUseCase createUser, UpdateUserUseCase updateUser, DeleteUserUseCase deleteUser,
                          ListUsersUseCase listUsers, GetUserUseCase getUser) {
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
        this.listUsers = listUsers;
        this.getUser = getUser;
    }

    // Mantener compatibilidad con endpoints existentes:
    @PostMapping("/consultarUsuarios")
    public List<UserResponse> consultarUsuarios() {
        return listUsers.execute().stream()
                .map(u -> new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getRole()))
                .collect(Collectors.toList());
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<UserResponse> crearUsuario(@Valid @RequestBody UserRequest req) {
        var saved = createUser.execute(new User(null, req.getName(), req.getEmail(), req.getRole()));
        return ResponseEntity.ok(new UserResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole()));
    }

    @PostMapping("/editarUsuario")
    public ResponseEntity<UserResponse> editarUsuario(@Valid @RequestBody UserRequest req, @RequestParam Integer id) {
        var updated = updateUser.execute(new User(id, req.getName(), req.getEmail(), req.getRole()));
        return ResponseEntity.ok(new UserResponse(updated.getId(), updated.getName(), updated.getEmail(), updated.getRole()));
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        deleteUser.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultarUsuario/{id}")
    public UserResponse consultarUsuarioPorId(@PathVariable Integer id) {
        var u = getUser.execute(id);
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getRole());
    }
}
