/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.capanegocio.modelo;

/**
 *
 * @author vsfs2
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
