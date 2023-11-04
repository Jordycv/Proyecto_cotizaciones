package com.codigo.msordencompra.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "ordencompra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idrespuestarequerimiento;
    private LocalDate fechaemision;
    private int estado;
    private String observaciones;
    @OneToMany(mappedBy = "ordencompra", cascade = CascadeType.ALL)
    private List<OrdenCompraDetalle> detalleOrden;



}
