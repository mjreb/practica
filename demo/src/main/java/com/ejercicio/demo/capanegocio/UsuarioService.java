/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.capanegocio;

import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capanegocio.modelo.UsuarioDTO;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Maria de Jesus Rebolledo Bustillo 
 * Clase que tienne el acceso al repositorio que persiste la base de datos con JPA. 
 */
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository; 
    
    public UsuarioDTO createUser(Usuario usuario){    
        repository.save(usuario); 
        
        UsuarioDTO dto = new UsuarioDTO(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getRol()
            );
        
        return dto;
        
    }
    
    public void deleteUser(Integer id){
        repository.deleteById(id);
        
    }
     
    
    public void updateUser(){
        
    }
    
    public List<Usuario> consultarUsuarios(){
        return repository.findAll();
        
    }
    
    public Usuario consultarUsuarioPorId(Integer id){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        
        return usuario;
        
    }
    
    public boolean validarContrasena(String password, Usuario usuario){
        String contrasenaReal = usuario.getContrasena();
        if (contrasenaReal.equals(password)){
            return true; 
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }
  
    }
    
    
    
    
    

}
