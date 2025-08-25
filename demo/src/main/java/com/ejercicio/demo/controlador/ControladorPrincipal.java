/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.controlador;

import com.ejercicio.demo.Util.JwtConfiguracion;
import com.ejercicio.demo.capanegocio.UsuarioService;
import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capanegocio.modelo.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    /*
    @PostMapping("/consultarUsuarios")
    public ResponseEntity <List<Usuario>> consultarUsuarios(@RequestHeader("Authorization") String token){
        
        try{
                validarRol(token, "ADMIN");
                return ResponseEntity.ok(service.consultarUsuarios());  
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            
            }

     }
    */
    
    @PostMapping("/crearUsuario")
    public ResponseEntity <UsuarioDTO> crearUsuario(@RequestBody Usuario usuario ){
        
        try{
                UsuarioDTO usuarioDTO = service.createUser(usuario);
                 return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);  
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            
            }

     }
    
    
    
    
    
    public boolean validarRol (String token, String rol){
        String rolExtraido =  configuracion.extraerRol(token);
        
        if (rolExtraido  .equals(rol)){
            return true;
        } else {
            throw new SecurityException("Acceso denegado. Rol requerido: " + rol);
        }
                 
        }

     
    
    
    }
    
   
    
    

