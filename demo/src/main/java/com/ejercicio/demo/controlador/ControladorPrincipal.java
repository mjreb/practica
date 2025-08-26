/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.controlador;

import com.ejercicio.demo.Util.JwtConfiguracion;
import com.ejercicio.demo.capanegocio.UsuarioService;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capanegocio.modelo.UsuarioDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Maria de Jesus Rebolledo Bustillo 
 * 
 * Controlador REST que tiene los endpoints 
 */

@RestController
@RequestMapping("/usuarios")
public class ControladorPrincipal {
    
    @Autowired
    private UsuarioService service;
    
    @Autowired
    private JwtConfiguracion configuracion; 
    
    
    @PostMapping("/consultarUsuarios")
    public ResponseEntity <List<UsuarioDTO>> consultarUsuarios(){       
                List<UsuarioDTO> usuarios = service.consultarUsuarios();
                return ResponseEntity.ok(usuarios);  
     }
    
    
    @PostMapping("/crearUsuario")
    public ResponseEntity <?> crearUsuario(@RequestBody Usuario usuario, @RequestHeader("Authorization") String header){
        
        try{
                validarRol(header, "ADMIN");
                UsuarioDTO usuarioDTO = service.createUser(usuario);
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);  
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
            
            } 

     }
        
    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity <String> eliminarUsuario(@PathVariable("id") Integer idUsuario, @RequestHeader("Authorization") String header){
        try {validarRol(header, "ADMIN");
        service.deleteUser(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado exitosamente");
        }catch (Exception e){
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); 
        }
    
    }
    
    
    @PostMapping("/editarUsuario")
    public ResponseEntity <?> editarUsuario(@RequestHeader("Authorization") String header, @RequestBody Usuario usuario){       
               try{
                validarRol(header, "ADMIN");
                Usuario usuarioModificado = service.updateUser(usuario);
                return ResponseEntity.ok(usuarioModificado); 
               }catch(Exception e){ 
                   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); 
               }          
     }
    

    public boolean validarRol (String header, String rol){
        
        // 1. Quitar el prefijo "Bearer "
            String token = header.replace("Bearer ", "").trim();
            System.out.println("El token antes del trim es: " + header);
            System.out.println("El token después del trim es: " + token);
        
        String rolExtraido =  configuracion.extraerRol(token);
        System.out.println("El rol extraído es: "+rolExtraido);
        
        if (rolExtraido.equals(rol)){
            
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Se necesita ser administrador para acceder a esta funcionalidad");
            }
                 
        }

     
    
    
    }
    
   
    
    

