package com.codigo.msproductos.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requerimiento")
public class Requerimiento {

    @Id
    @Column(name = "idreq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idreq;
    @Column(name = "fechaemision")
    private LocalDate fechaemision;
    @Column(name = "usuemision")
    private int usuemision;
    @Column(name = "fechaaprobacion")
    private LocalDate fechaaprobacion;
    @Column(name = "usuaprobacioon")
    private int usuaprobacioon;
    @Column(name = "fechaobservado")
    private LocalDate fechaobservado;
    @Column(name = "usuobservado")
    private int usuobservado;
    @Column(name = "descripcionobservacion")
    private String descripcionobservacion;
    @Column(name = "fechaanulado")
    private LocalDate fechaanulado;
    @Column(name = "fechavencimiento")
    private LocalDate fechavencimiento;
    @Column(name = "usuanulado")
    private int usuanulado;
    @Column(name = "estado")
    private int estado;

    @OneToMany(mappedBy = "requerimiento", cascade = CascadeType.ALL)
    private List<RequerimientoDetalle> detalleReq;
}
