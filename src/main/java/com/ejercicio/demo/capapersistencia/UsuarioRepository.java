/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ejercicio.demo.capapersistencia;

import com.ejercicio.demo.capanegocio.modelo.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para la entidad
 * Extiende de JpaRepository, lo cual proporciona métodos CRUD
 * predeterminados para interactuar con la base de datos
 * @author Maria de Jesus Rebolledo Bustillo
 */
@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{
    @Override
    List<Usuario> findAll();
    Usuario findByIdUsuario(Integer id);

    
}
