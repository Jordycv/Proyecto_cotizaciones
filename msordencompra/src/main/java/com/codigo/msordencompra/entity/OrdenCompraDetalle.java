package com.codigo.msordencompra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "ordencompradetalle")
public class OrdenCompraDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int productoid;
    private Double cantidad;
    private Double precio;

    @JsonIgnore // Para evitar bucles
    @ManyToOne
    @JoinColumn(name = "ordencompraid", nullable = false )
    private OrdenCompra ordenCompra;


}
