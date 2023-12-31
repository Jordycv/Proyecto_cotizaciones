package com.codigo.msrespuestacot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int productoid;
    private Double cantidad;
    private Double precio;

    @JsonIgnore // Para evitar bucles
    @ManyToOne
    @JoinColumn(name = "respuestarequerimientoid", nullable = false )
    private RespuestaRequerimiento respuestaRequerimiento;

}
