package com.codigo.msproductos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requerimientodetalle")
public class RequerimientoDetalle {

    @Id
    @Column(name = "iddet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddet;
    @Column(name = "productoid")
    private int productoid;
    @Column(name = "cantidad")
    private int cantidad;

    @JsonIgnore // Para evitar bucles
    @ManyToOne
    @JoinColumn(name = "idreq", nullable = false )
    private Requerimiento requerimiento;



}
