package com.codigo.mslogincot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@NamedQuery(name = "Usuario.findByUsuario", query = "select u from Usuario u where u.usuario=:usuario")

@Entity
@Getter
@Setter
@Where(clause = "estado = 1")
@DynamicInsert
@DynamicUpdate
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String usuario;
    private String password;
    private int estado;
    private Date fechacrea;
    private Date fechamod;
    private int personaid;
    private String rol;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Roles> roles = new ArrayList<>();

}
