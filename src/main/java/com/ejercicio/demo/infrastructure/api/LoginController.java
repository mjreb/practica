package com.ejercicio.demo.infrastructure.api;

import com.ejercicio.demo.application.usecase.LoginUseCase;
import com.ejercicio.demo.infrastructure.api.dto.LoginRequestDto;
import com.ejercicio.demo.infrastructure.api.dto.TokenResponse;
import com.ejercicio.demo.util.JwtConfiguracion;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginUseCase loginUseCase;
    private final JwtConfiguracion jwt;

    public LoginController(LoginUseCase loginUseCase, JwtConfiguracion jwt) {
        this.loginUseCase = loginUseCase;
        this.jwt = jwt;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequestDto req) {
        var user = loginUseCase.execute(req.getEmail(), req.getPassword());
        String token = jwt.generarToken(user.getEmail(), user.getRole()); // método hipotético
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
