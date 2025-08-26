/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.capanegocio.modelo;

/**
 *  Clase que representa la petición de inicio de sesión (login).
 * Se utiliza para encapsular las credenciales ingresadas por el usuario, 
 * las cuales incluyen su identificador único y su contraseña.
 * 
 * Este objeto se recibe en el cuerpo de la petición HTTP 
 * cuando un usuario intenta autenticarse.
 * @author María de Jesús Rebolledo Bustillo
 */
public class LoginRequest {
    private Integer idUsuario;
    private String contrasena;

    // Getters y setters
    public Integer getIdUsuario() { 
        return idUsuario; 
    }
    public void setIdUsuario(Integer idUsuario) { 
        this.idUsuario = idUsuario; 
    }

    public String getContrasena() { 
        return contrasena; 
    }
    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena; 
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "idUsuario=" + idUsuario +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
    
}
