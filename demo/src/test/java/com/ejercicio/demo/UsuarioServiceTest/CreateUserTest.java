/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.UsuarioServiceTest;

import com.ejercicio.demo.capanegocio.UsuarioService;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capanegocio.modelo.UsuarioDTO;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * Prueba unitaria para el método createUser de la clase UsuarioService.
 * Se usa Mockito para simular el comportamiento del repositorio.
 * @author Maria de Jesús Rebolledo Bustillo
 */
public class CreateUserTest {
    
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
     * Test para createUser: debe guardar el usuario y devolver un DTO sin contraseña.
     */
    @Test
    void testCreateUser() {
        when(repository.save(usuario)).thenReturn(usuario);

        UsuarioDTO dto = service.createUser(usuario);

        assertEquals(usuario.getIdUsuario(), dto.getIdUsuario());
        assertEquals(usuario.getNombre(), dto.getNombre());
        assertEquals(usuario.getEmail(), dto.getEmail());
        assertEquals(usuario.getRol(), dto.getRol());
        
    }

    
}
