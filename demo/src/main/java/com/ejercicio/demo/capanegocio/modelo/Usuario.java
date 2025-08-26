/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.capanegocio.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;




/**
 * Entidad que representa a un usuario en el sistema.
 * Se mapea a la tabla "usuario" de la base de datos
 * @author Maria de Jesús Rebolledo Bustillo
 */
@Entity
@Table (name = "usuario")
public class Usuario {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_usuario")
        private Integer idUsuario;
        
        @NotNull
        @Size(min = 1, max = 100)
        @Column(name = "nombre")
        private String nombre;
        
        @NotNull
        @Size(min = 1, max = 100)
        @Column(name = "email", unique =  true)
        private String email;
        
        @NotNull
        @Size(min = 1, max = 100)
        @Column(name = "contrasena")
        private String contrasena;
        
        @Column(name = "rol")
        private String rol;
        
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public int getIdUsuario() {
        return idUsuario;
    }

 
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    @Override
    public String toString() {
        return "Usuario:\n" +
                "  idUsuario = " + idUsuario + "\n" +
                "  nombre    = " + nombre + "\n" +
                "  email     = " + email + "\n" +
                "  rol       = " + rol + "\n";
    }

    
}
