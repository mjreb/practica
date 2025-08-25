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
 *
 * @author vsfs2
 */
@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{
    @Override
    List<Usuario> findAll();
    Usuario findByIdUsuario(Integer id);

    
}
