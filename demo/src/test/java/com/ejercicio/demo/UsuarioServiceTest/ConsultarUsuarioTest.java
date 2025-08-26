/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.UsuarioServiceTest;

import com.ejercicio.demo.capanegocio.UsuarioService;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capanegocio.modelo.UsuarioDTO;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * Prueba unitaria para el método deleteUser de la clase UsuarioService.
 * Se usa Mockito para simular el comportamiento del repositorio.
 * @author Maria de Jesús Rebolledo Bustillo
 */

public class ConsultarUsuarioTest {
    
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
     * Test para consultarUsuarios: devuelve lista de DTOs.
     * Comprobamos que el método devuelva una lista con todos los elementos en el orden establecido. 
     */
    @Test
    void testConsultarUsuarios() {
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2);
        usuario2.setNombre("Ana");
        usuario2.setEmail("ana@gmail.com");
        usuario2.setContrasena("9999");
        usuario2.setRol("USER");

        when(repository.findAll()).thenReturn(Arrays.asList(usuario, usuario2));

        List<UsuarioDTO> result = service.consultarUsuarios();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdUsuario());
        assertEquals(2, result.get(1).getIdUsuario());
        
    
    }
    
}