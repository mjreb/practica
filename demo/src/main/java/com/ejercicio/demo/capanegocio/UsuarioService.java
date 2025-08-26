/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.capanegocio;

import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capanegocio.modelo.UsuarioDTO;
import com.ejercicio.demo.capapersistencia.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Maria de Jesus Rebolledo Bustillo 
 * Servicio que maneja la lógica de negocio relacionada con la entidad
 * Se conecta con el repositorio (UsuarioRepository) para acceder a la base de datos
 * usando JPA
 */
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository; 
    
     /**
     * Crea un nuevo usuario en la base de datos y retorna un UsuarioDTO 
     * con la información relevante y evitar exponer la contraseña.
     *
     * @param usuario objeto de tipo (Usuario) con los datos a persistir
     * @return un UsuarioDTO con id, nombre, email y rol del usuario creado
     */
    
    
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
    
    /**
     * Elimina un usuario de la base de datos a partir de su id.
     * 
     * Antes de eliminarlo, se valida que el usuario exista.
     *
     * @param id identificador único del usuario a eliminar
     * @throws ResponseStatusException si el usuario no existe
     */
    
    public void deleteUser(Integer id){
        consultarUsuarioPorId(id);
        repository.deleteById(id);  
    }
     
    /**
     * Actualiza los datos de un usuario existente en la base de datos.
     *
     * @param usuario objeto Usuario con los datos modificados
     * @return el Usuario actualizado y persistido en la base de datos
     * @throws ResponseStatusException si el usuario no existe
     */
    public Usuario updateUser(Usuario usuario){
        Usuario usuarioExistente = consultarUsuarioPorId(usuario.getIdUsuario());
        usuarioExistente.setContrasena(usuario.getContrasena());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setRol(usuario.getRol());
        
        return repository.save(usuarioExistente);
        
        
    }
    
    /**
     * Consulta todos los usuarios registrados en la base de datos.
     *
     * @return lista de objetos suarioDTO con los datos principales de cada usuario (no expone la contraseña)
     */
    
    public List<UsuarioDTO> consultarUsuarios(){
        
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList();
        
        for (Usuario u: usuarios){
            UsuarioDTO usuarioDTO = new UsuarioDTO(u.getIdUsuario(),u.getNombre(), u.getEmail(), u.getRol());
            usuariosDTO.add(usuarioDTO);
        }
        
        return usuariosDTO;
        
    }
    
     /**
     * Consulta un usuario por su id.
     *
     * @param id identificador único del usuario
     * @return el objeto  Usuario encontrado
     * @throws ResponseStatusException si el usuario no existe
     */
    
    public Usuario consultarUsuarioPorId(Integer id){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        
        return usuario;
        
    }
    
    /**
     * Valida si la contraseña ingresada corresponde con la del usuario dado.
     *
     * @param password contraseña ingresada para validar
     * @param usuario objeto Usuario con la contraseña real
     * @return true si la contraseña es correcta
     * @throws ResponseStatusException si la contraseña no coincide
     */
    
    public boolean validarContrasena(String password, Usuario usuario){
        String contrasenaReal = usuario.getContrasena();
        if (contrasenaReal.equals(password)){
            return true; 
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }
  
    }
    
}
