package com.codigo.msrespuestacot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "respuestarequerimiento")
public class RespuestaRequerimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idrequerimiento;
    private int proveedorid;
    private LocalDate fecharespuesta;
    private Double total;
    private String observaciones;
    @OneToMany(mappedBy = "respuestaRequerimiento", cascade = CascadeType.ALL)
    private List<RespuestaRequerimientoDetalle> detalleReq;

}
