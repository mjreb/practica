/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio.demo.Util;

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
     * Tiempo de expiración del token es una hora
     */
    private final long expirationMs = 1000 * 60 * 60;
    
    /**
     * Método para generar el token
     * @param id del usuario
     * @param nombre del usuario
     * @param rol que tiene el usuario (ADMIN o USER)
     * @return 
     */
    public String generarToken(Integer id, String nombre, String rol){
        return Jwts.builder()
                .setSubject(nombre) // Registered claim 
                .claim("rol", rol) // Public claim
                .claim("id", id) // Public claim 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }
    
    /**
     * Método para validar si el token está expirado o ha sido modificado.
     * Construyo un objetoparserBuilder() para leer y validar el token. Asigno la llave con
     * la que debe alidar el token con .setSigningKey(secretKey). 
     * .parseClaimsJws(token) revisa que la firma se válida, que el token no esté expirado y que la estructura sea correcta. 
     * @param token
     * @return 
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
     * 
     * @param token
     * @return 
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
