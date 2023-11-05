package com.codigo.msordencompra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class RespuestaRequerimiento {

    private Long id;
    private int idrequerimiento;
    private int proveedorid;
    private LocalDate fecharespuesta;
    private Double total;
    private String observaciones;
    @OneToMany(mappedBy = "respuestaRequerimiento", cascade = CascadeType.ALL)
    private List<RespuestaRequerimientoDetalle> detalleReq;
}
