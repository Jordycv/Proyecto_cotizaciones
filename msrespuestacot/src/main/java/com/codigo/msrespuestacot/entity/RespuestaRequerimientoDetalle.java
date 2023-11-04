package com.codigo.msrespuestacot.entity;

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
@Table(name = "respuestarequerimientodetalle")
public class RespuestaRequerimientoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String RespuestaRequerimientoId;
    private String ProductoId;
    private Date Cantidad;
    private Double Precio;
    private String Observaciones;

}
