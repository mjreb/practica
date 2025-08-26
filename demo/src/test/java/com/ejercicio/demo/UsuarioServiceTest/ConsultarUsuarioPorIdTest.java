/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.UsuarioServiceTest;

import com.ejercicio.demo.capanegocio.UsuarioService;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

/**
 * Prueba unitaria para el método validarContrasena de la clase UsuarioService.
 * Se usa Mockito para simular el comportamiento del repositorio.
 * @author Maria de Jesus Rebolledo Bustillo
 */
public class ConsultarUsuarioPorIdTest {
    
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
     * Test para consultarUsuarioPorId: devuelve un objeto Usuaio no nulo si
     * es que existe en la BD. .
     */
    @Test
    void testConsultarUsuarioPorIdSuccess() {
        when(repository.findById(1)).thenReturn(Optional.of(usuario));
        Usuario result = service.consultarUsuarioPorId(1);
        assertNotNull(result);
    }

    /**
     * Test para consultarUsuarioPorId: lanza excepción si no existe un usuario con ese id.
     */
    @Test
    void testConsultarUsuarioPorIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.consultarUsuarioPorId(1));
    }
    
}
