package com.codigo.msrequerimiento.dto;

import com.codigo.msrequerimiento.entity.RequerimientoDetalle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequrimientoDto {

    private int idreq;
    private List<RequerimientoDetalle> detalleReq;
}
