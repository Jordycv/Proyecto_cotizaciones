package com.codigo.msrequerimiento.service;

import com.codigo.msrequerimiento.dto.EmisionDto;
import com.codigo.msrequerimiento.dto.ObservacionDto;
import com.codigo.msrequerimiento.dto.RequestDto;
import com.codigo.msrequerimiento.dto.RequrimientoDto;
import com.codigo.msrequerimiento.entity.Requerimiento;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RequerimientoService {

    Requerimiento emision(EmisionDto t, HttpServletRequest header);
    Requerimiento aprobacion(RequestDto t, HttpServletRequest header);
    Requerimiento observacion(ObservacionDto t, HttpServletRequest header);
    Requerimiento anulacion(RequestDto t, HttpServletRequest header);

    List<RequrimientoDto> listarReq(HttpServletRequest header);

    RequrimientoDto obtenerxId(int id, HttpServletRequest header);
}
