package com.codigo.msrespuestacot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

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
    private String idrequerimiento;
    private String proveedorid;
    private Date fecharespuesta;
    private Double total;
    private String Observaciones;


}
