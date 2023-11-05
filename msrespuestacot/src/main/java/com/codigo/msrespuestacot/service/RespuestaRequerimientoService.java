package com.codigo.msrespuestacot.service;

import com.codigo.msrespuestacot.entity.RespuestaRequerimiento;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RespuestaRequerimientoService {

    RespuestaRequerimiento save(RespuestaRequerimiento t, HttpServletRequest header);
    RespuestaRequerimiento obtenerxId(Long id, HttpServletRequest header);;
}
