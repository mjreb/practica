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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

/**
 *Prueba unitaria para el método updateUser de la clase UsuarioService.
 * Se usa Mockito para simular el comportamiento del repositorio.
 * @author Maria de Jesus Rebolledo Bustillo
 */
public class UpdateUserTest {
    
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
        usuario.setEmail("juan@test.com");
        usuario.setContrasena("1234");
        usuario.setRol("USER");
    }
    
    /**
     * Prueba para verificar que el método devuelva un objeto con los campos
     * modificados correctamente. 
     */
    
    @Test
    void updateUserSucess(){ 
        // Creamos un objeto Usuario con los datos actualizados
        Usuario actualizado = new Usuario();
        actualizado.setIdUsuario(1);
        actualizado.setNombre("Pedro");
        actualizado.setEmail("pedro@gmail.com");
        actualizado.setContrasena("5678");
        actualizado.setRol("ADMIN");
        
        
        // Configuramos el mock del repositorio:
        // Cuando se busque el usuario por ID, devolvemos el usuario original
         when(repository.findById(1)).thenReturn(Optional.of(usuario));
        // Cuando se guarde un usuario, devolvemos el usuario actualizado simulado 
         when(repository.save(any(Usuario.class))).thenReturn(actualizado);
        
         Usuario result = service.updateUser(actualizado);
         
         // Verificamos que cada campo haya sido actualizado correctamente
        assertEquals(actualizado.getNombre(), result.getNombre());
        assertEquals(actualizado.getEmail(), result.getEmail());
        assertEquals(actualizado.getContrasena(), result.getContrasena());
        assertEquals(actualizado.getRol(), result.getRol());
    }
    
    
    
}
