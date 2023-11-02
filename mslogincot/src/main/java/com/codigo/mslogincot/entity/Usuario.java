package com.codigo.mslogincot.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@NamedQuery(name = "Usuarios.findByUsuario", query = "select u from Usuarios u where u.usuario=:usuario")

@Entity
@Getter
@Setter
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
    private Date fechaCrea;
    private Date fechaMod;
    private int personaId;
    private String rol;
}
