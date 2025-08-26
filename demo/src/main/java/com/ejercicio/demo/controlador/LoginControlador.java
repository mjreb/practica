/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.controlador;

import com.ejercicio.demo.util.JwtConfiguracion;
import com.ejercicio.demo.capanegocio.UsuarioService;

import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.ejercicio.demo.capanegocio.modelo.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador encargado del proceso de autenticación (login).
 * 
 * Este controlador recibe credenciales de un usuario, valida la contraseña
 * contra la base de datos y genera un token JWT que se utilizará en las
 * siguientes peticiones para autorización.
 * @author Maria de Jesus Rebolledo Bustillo
 */
@RestController
@RequestMapping("/login")
public class LoginControlador {

    public LoginControlador(UsuarioService service, JwtConfiguracion configuracion) {
        this.service = service;
        this.configuracion = configuracion;
    }

    
    private final JwtConfiguracion configuracion; 
    
    private final UsuarioService service;
    
    /**
     * Realiza el proceso de login de un usuario.
     * 
     *   Recibe un objeto LoginRequest con idUsuario y contraseña.
     *   Consulta al usuario en base de datos mediante UsuarioService.
     *   Valida la contraseña con  validarContrasena.
     *   Genera un token JWT con la información del usuario (id, nombre y rol).
     * 
     * 
     * @param datos Objeto con las credenciales de acceso (idUsuario y contraseña).
     * @return Token JWT si la autenticación es exitosa, o el mensaje de error si falla.
     */
    
    @PostMapping
    public String login(@RequestBody LoginRequest datos) {
        System.out.print(datos);
        try{
        Usuario usuario = service.consultarUsuarioPorId(datos.getIdUsuario());
        boolean validacion = service.validarContrasena(datos.getContrasena(), usuario);
        String token = configuracion.generarToken(usuario.getIdUsuario(), usuario.getNombre(), usuario.getRol());
        System.out.print(token);
        return token;
        
        }catch(Exception e){
            return e.getMessage();
        }

        
    }
    
    
}