package com.codigo.mslogincot.service.impl;

import com.codigo.mslogincot.constantes.Constantes;
import com.codigo.mslogincot.dao.UsuarioDAO;
import com.codigo.mslogincot.entity.Usuario;
import com.codigo.mslogincot.security.CustomerDetailService;
import com.codigo.mslogincot.security.Jwt.JwtUtil;
import com.codigo.mslogincot.service.UsuarioService;
import com.codigo.mslogincot.util.LoginUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailService customerDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Ingreso Login : " + requestMap);

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("usuario"),requestMap.get("contrasena")));

            if(authentication.isAuthenticated()){
                if(customerDetailsService.getUserDetail().getEstado() == 1 ){
                    return new ResponseEntity<String>(
                            "{\"token \":\"" + jwtUtil.generateToken(customerDetailsService.getUserDetail().getUsuario(),
                                    customerDetailsService.getUserDetail().getRol()) +"\"}",
                            HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<String>("{\"mensaje\": " + "Espera la Aprobación del administrador"+"\"}",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("{}", e);
        }
        return new ResponseEntity<String>("{\"mensaje\": " + "Credenciales Incorrectas"+"\"}",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> registrarUsuario(Map<String, String> requestMap) {
        log.info("Registro Interno de un Usuario : " + requestMap);
        try {
            if(validatePrevioRegistro(requestMap)){
                Usuario usuario = usuarioDAO.findByUsuario(requestMap.get("usuario"));
                if(Objects.isNull(usuario)){
                    usuarioDAO.save(getUsuariosMap(requestMap));
                    return LoginUtils.getResponseEntity("Usuario Registrado con éxito", HttpStatus.CREATED);
                }else{
                    return LoginUtils.getResponseEntity("Usuario Ya existe", HttpStatus.BAD_REQUEST);
                }
            }else {
                return LoginUtils.getResponseEntity(Constantes.DATA_INVALIDA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return LoginUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public List<Usuario> obtenerAllUsuarios() {
        return usuarioDAO.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuario(Integer id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public Optional<Usuario> updateUsuario(Integer id, Usuario usuario) {
        if (usuarioDAO.existsById(usuario.getId())) {
            Usuario usuarioExistente=obtenerUsuario(id).get();
            usuarioExistente.setUsuario(usuario.getUsuario());
            usuarioExistente.setEstado(usuario.getEstado());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setRol(usuario.getRol());
            usuarioExistente.setFechaMod(new Date());
            usuarioExistente.setPersonaId(usuario.getPersonaId());

            return Optional.of(usuarioDAO.save(usuarioExistente));
        }
        return Optional.empty();
    }

    @Override
    public Usuario deleteUsuario(Integer id) {
        return null;
    }

    private Boolean validatePrevioRegistro(Map<String, String> requestMap){
        if(requestMap.containsKey("usuario")
                && requestMap.containsKey("password")
                && requestMap.containsKey("persona_id")){
            return true;
        }
        return false;
    }
    private Usuario getUsuariosMap(Map<String, String> requestMap){
        Usuario usuario = new Usuario();
        usuario.setUsuario(requestMap.get("usuario"));
        usuario.setPassword(requestMap.get("password"));
        usuario.setEstado(Constantes.ACTIVO);
        usuario.setPersonaId(Integer.parseInt(requestMap.get("persona_id")));
        usuario.setFechaCrea(new Date());
        return usuario;

    }
}
