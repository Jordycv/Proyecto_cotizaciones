package com.codigo.mslogincot.security;

import com.codigo.mslogincot.constantes.Constantes;
import com.codigo.mslogincot.dao.UsuarioDAO;
import com.codigo.mslogincot.entity.Roles;
import com.codigo.mslogincot.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            /*Roles roles=new Roles();
            roles.setRolename(Constantes.ROLE_USUARIO);
            Roles roles1=new Roles();
            roles1.setRolename(Constantes.ROLE_ADMIN);
            userDetail.getRoles().add(roles1);
            userDetail.getRoles().add(roles);*/
            Collection<? extends GrantedAuthority> authorities =
                    userDetail.
                            getRoles().
                            stream().
                            map(roles2 -> new SimpleGrantedAuthority(roles2.getRolename())).collect(Collectors.toList());
            return new User(userDetail.getUsuario(), userDetail.getPassword(), authorities);
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    public Usuario getUserDetail() {
        return userDetail;
    }
}
