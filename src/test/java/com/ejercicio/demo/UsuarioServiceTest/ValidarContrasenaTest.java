/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.UsuarioServiceTest;

import com.ejercicio.demo.capanegocio.UsuarioService;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

/**
 * Prueba unitaria para el método validarContrasena de la clase UsuarioService.
 * Se usa Mockito para simular el comportamiento del repositorio.
 * @author Maria de Jesus Rebolledo Bustillo
 */
public class ValidarContrasenaTest {
    
    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks de Mockito
        MockitoAnnotations.openMocks(this);
        // Configuración del usuario de prueba
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");
        usuario.setEmail("juan@gmail.com");
        usuario.setContrasena("1234");
        usuario.setRol("USER");
    }
    
    /**
     * Test para validarContrasena: devuelve true si coincide.
     */
    @Test
    void testValidarContrasenaSuccess() {
        boolean result = service.validarContrasena("1234", usuario);
        assertTrue(result);
    }

    /**
     * Test para validarContrasena: lanza excepción si no coincide.
     */
    @Test
    void testValidarContrasenaFail() {
        assertThrows(ResponseStatusException.class,
                () -> service.validarContrasena("wrong", usuario));
    }
    
}
