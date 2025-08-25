/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.controlador;

import com.ejercicio.demo.Util.JwtConfiguracion;
import com.ejercicio.demo.capanegocio.UsuarioService;

import com.ejercicio.demo.capanegocio.modelo.Usuario;
import com.example.demo.capanegocio.modelo.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vsfs2
 */
@RestController
@RequestMapping("/login")
public class LoginControlador {
    
    @Autowired 
    private JwtConfiguracion configuracion; 
    
    @Autowired 
    private UsuarioService service;
    
    
    
    @PostMapping("/hola")
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

        
        // Para simplificar: no usamos contraseña
        
    }
    
    
}
