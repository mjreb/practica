/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;


/**
 *
 * @author Maria de Jesús Rebolledo Bustillo
 * 
 * Clase para generar, validar y extraer la información del jwt. 
 */
@Component 
public class JwtConfiguracion {
    
    /** 
     * Clave secreta que spring va a generar cada que se ejecute la aplicacion.
     * El algoritmo utilizado es SignatureAlgorithm.HS256. 
     */
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    /**
     * Tiempo de expiración del token es una hora en milisegundos
     */
    private final long expirationMs = 1000 * 60 * 60;
    
    /**
     * Método para generar el token JWT con la informació del usuario
     * @param id Identificador único del usuario.
     * @param nombre Nombre del usuario (se guarda como subject).
     * @param rol Rol del usuario (ejemplo: ADMIN o USER).
     * @return Token JWT firmado con la clave secreta.
     */
    public String generarToken(Integer id, String nombre, String rol){
        return Jwts.builder()
                .setSubject(nombre) // Registered claim 
                .claim("rol", rol) // Public claim
                .claim("id", id) // Public claim 
                .setIssuedAt(new Date()) // fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // tiempo de expiración
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }
    
    /**
     * Método para validar si el token es correcto.
     * Comprueba que la firma sea válida, que no esté expirado y que la estructura del JWT sea coorrecta.
     *
     * Construyo un objetoparserBuilder() para leer y validar el token. Asigno la llave con
     * la que debe validar el token con .setSigningKey(secretKey). 
     * .parseClaimsJws(token) revisa que la firma sea válida, que el token no esté expirado y que la estructura sea correcta. 
     * @param token Token JWT a validar.
     * @return true si el token es válido, false en caso contrario.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Extrae el rol de un token JWT válido.
     * 
     * @param token Token JWT firmado previamente.
     * @return Rol contenido en el claim rol.
     */
      public String extraerRol(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }
    

}
