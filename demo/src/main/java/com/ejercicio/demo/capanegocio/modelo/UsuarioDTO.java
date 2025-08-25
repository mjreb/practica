/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.capanegocio.modelo;

import org.springframework.stereotype.Component;

/**
 *
 * @author Maria de Jesus Rebolledo Bustillo
 * Data Object Tranfer de Usuario que impide que información innecesaria (password) se revele en la respuesta. 
 */

public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String email;
    private String rol;

    public UsuarioDTO(Integer idUsuario, String nombre, String email, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }
    
}
