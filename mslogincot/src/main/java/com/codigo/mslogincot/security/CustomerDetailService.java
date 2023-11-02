package com.codigo.mslogincot.security;

import com.codigo.mslogincot.dao.UsuarioDAO;
import com.codigo.mslogincot.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;
    private Usuario userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetail = usuarioDAO.findByUsuario(username);
        if(!Objects.isNull(userDetail)){
            return new User(userDetail.getUsuario(), userDetail.getPassword(), new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    public Usuario getUserDetail() {
        return userDetail;
    }
}
