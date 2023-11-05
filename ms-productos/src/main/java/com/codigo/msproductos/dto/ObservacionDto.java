package com.codigo.msproductos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ObservacionDto {

    private int idreq;
    private int usuobservado;
    private String descripcionobservacion;

}
