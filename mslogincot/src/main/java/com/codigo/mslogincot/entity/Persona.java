package com.codigo.mslogincot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String areaId;
    private int estado;
    private Date fechaCrea;
    private Date fechaMod;


}
