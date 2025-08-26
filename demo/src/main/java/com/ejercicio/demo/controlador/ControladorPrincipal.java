/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.controlador;

import com.ejercicio.demo.util.JwtConfiguracion;
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
 * Controlador REST para la gestión de usuarios.
 * Proporciona endpoints para consultar, crear, eliminar y editar usuarios.
 * Cada operación valida el rol del usuario a través de un token JWT
 * Los endpoints devuelven información en formato JSON que SpringBoot convierte automáticamente. 
 */

@RestController
@RequestMapping("/usuarios")
public class ControladorPrincipal {

    public ControladorPrincipal(UsuarioService service, JwtConfiguracion configuracion) {
        this.service = service;
        this.configuracion = configuracion;
    }

    
    private final UsuarioService service;
    
    private final JwtConfiguracion configuracion; 
    
    /**
     * Consulta la lista de usuarios registrados.
     * Tanto usuarios normales como administradores pueden consultarlo. 
     * @return ResponseEntity con la lista de usuarios en formato DTO.
     */
    
    @PostMapping("/consultarUsuarios")
    public ResponseEntity <List<UsuarioDTO>> consultarUsuarios(){       
                List<UsuarioDTO> usuarios = service.consultarUsuarios();
                return ResponseEntity.ok(usuarios);  
     }
    
    
    /**
     * Crea un nuevo usuario en el sistema. Este método sólo es válido para administradores.
     *
     * @param usuario Datos del usuario a crear que se mapean automáticamente a un objeto Usuario.
     * @param header Token JWT del usuario que realiza la petición.
     * @return ResponseEntity con el usuario creado o un error en caso de fallo.
     */
    
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
       
    
    /**
     * Elimina un usuario dado su ID. Este método sólo es válido para administradores.
     *
     * @param idUsuario Identificador del usuario a eliminar que viene en el path.
     * @param header Token JWT del usuario que realiza la petición.
     * @return ResponseEntity con un mensaje de confirmación o error.
     */
    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity <String> eliminarUsuario(@PathVariable("id") Integer idUsuario, @RequestHeader("Authorization") String header){
        try {validarRol(header, "ADMIN");
        service.deleteUser(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado exitosamente");
        }catch (Exception e){
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); 
        }
    
    }
    
    /**
     * Edita los datos de un usuario existente. Método sólo válido para administradores debido a que se puede modificar el rol.
     *
     * @param header Token JWT del usuario que realiza la petición.
     * @param usuario Objeto con los datos a modificar.
     * @return ResponseEntity con el usuario modificado o un error en caso de fallo.
     */
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
    
    
    /**
     * Valida que el usuario que hace la petición tenga el rol requerido.
     *
     * @param header Token JWT enviado en la cabecera Authorization de la petición.
     * @param rol Rol esperado (por ejemplo, ADMIN).
     * @return true si el rol es válido.
     * @throws ResponseStatusException si el rol no coincide con el esperado.
     */
    public boolean validarRol (String header, String rol){
        
        // 1. Quitar el prefijo "Bearer " que se colocó por convención al momento se mandar la petición. 
        String token = header.replace("Bearer ", "").trim();
        
        // 2. Extraer el rol del token. 
        String rolExtraido =  configuracion.extraerRol(token);
       
        // 3. Validar si coincide con el rol esperado
        if (rolExtraido.equals(rol)){
            
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Se necesita ser administrador para acceder a esta funcionalidad");
            }
                 
        }

     
    
    
    }
    
   
    
    
