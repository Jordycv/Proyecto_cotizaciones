package com.codigo.mslogincot.dao;

import com.codigo.mslogincot.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    Usuario findByUsuario(@Param(("usuario")) String usuario);

    Optional<Usuario> findById(Integer Id);
}
