package com.codigo.msordencompra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class RespuestaRequerimientoDetalle {

    private Long id;
    private int productoid;
    private Double cantidad;
    private Double precio;

    @JsonIgnore // Para evitar bucles
    @ManyToOne
    @JoinColumn(name = "respuestarequerimientoid", nullable = false )
    private RespuestaRequerimiento respuestaRequerimiento;
}
