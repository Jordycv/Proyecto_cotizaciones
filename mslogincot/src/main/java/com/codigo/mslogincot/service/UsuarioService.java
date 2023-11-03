package com.codigo.mslogincot.service;

import com.codigo.mslogincot.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsuarioService {
    ResponseEntity<String> login(Map<String,String> requestMap);
    ResponseEntity<String> registrarUsuario(Map<String,String> requestMap);
    List<Usuario> obtenerAllUsuarios();
    Optional<Usuario> obtenerUsuario(Integer id);
    Optional<Usuario> updateUsuario(Integer id, Usuario usuario);
    Optional<Usuario>  deleteUsuario(Integer id);

}
