/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.UsuarioServiceTest;

import com.ejercicio.demo.capanegocio.UsuarioService;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * Prueba unitaria para el método deleteUser de la clase UsuarioService.
 * Se usa Mockito para simular el comportamiento del repositorio.
 * @author Maria de Jesús Rebolledo Bustillo
 */

public class DeleteUserTest {
    
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
     * Prueba para verificar que un usuario se elimine si es que existe.
     * Se verifica que se llame al método save sólo una vez. 
     */
    @Test
    void deleteUserSucess(){
        // Configuramos el mock del repositorio, que devuelve el objeto Usuario 
        // cuando se da su id
        when(repository.findById(1)).thenReturn(Optional.of(usuario));
        service.deleteUser(1);
        verify(repository, times(1)).deleteById(1);
    }
    
    
    /**
     * Prueba para verificar que el método devuelva la excepción
     * ResponseStatusException cuando se quiere eliminar un usuario que no existe.
     *
     */
    @Test
    void deleteUserFail(){
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.deleteUser(1));
    }
    
}
