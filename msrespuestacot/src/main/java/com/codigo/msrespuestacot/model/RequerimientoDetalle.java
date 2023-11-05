package com.codigo.msrespuestacot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class RequerimientoDetalle {

    private int iddet;

    private int productoid;

    private int cantidad;

    @JsonIgnore // Para evitar bucles
    @ManyToOne
    @JoinColumn(name = "idreq", nullable = false )
    private Requerimiento requerimiento;


}
