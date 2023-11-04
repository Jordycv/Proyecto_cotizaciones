package com.codigo.mslogincot.rest;

import com.codigo.mslogincot.constantes.Constantes;
import com.codigo.mslogincot.entity.Usuario;
import com.codigo.mslogincot.service.UsuarioService;
import com.codigo.mslogincot.util.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrarUsuarios")
    public ResponseEntity<String> registrarUsuarios(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return usuarioService.registrarUsuario(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return LoginUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/ObtenerAllUsuarios")
    public List<Usuario> allUsuarios(){
        try {
            return usuarioService.obtenerAllUsuarios();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/ObtenerUsuario")
    public Optional<Usuario> ObtenerUnUsuario(@RequestParam(required = true) Integer id){
        try {
            return usuarioService.obtenerUsuario(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return usuarioService.login(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return LoginUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario)
                .map(usur-> new ResponseEntity<>(usur, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Integer id){
        Usuario usuarioEl = usuarioService.deleteUsuario(id).get();
        return ResponseEntity.ok(usuarioEl);
    }
}
