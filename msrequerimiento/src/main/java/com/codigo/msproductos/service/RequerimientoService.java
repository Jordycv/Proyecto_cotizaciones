package com.codigo.msproductos.service;

import com.codigo.msproductos.dto.EmisionDto;
import com.codigo.msproductos.dto.ObservacionDto;
import com.codigo.msproductos.dto.RequestDto;
import com.codigo.msproductos.dto.RequrimientoDto;
import com.codigo.msproductos.entity.Requerimiento;
import com.codigo.msproductos.response.ResponseProductos;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface RequerimientoService {

    Requerimiento emision(EmisionDto t, HttpServletRequest header);
    Requerimiento aprobacion(RequestDto t, HttpServletRequest header);
    Requerimiento observacion(ObservacionDto t, HttpServletRequest header);
    Requerimiento anulacion(RequestDto t, HttpServletRequest header);

    List<RequrimientoDto> listarReq(HttpServletRequest header);

    RequrimientoDto obtenerxId(int id, HttpServletRequest header);
}
