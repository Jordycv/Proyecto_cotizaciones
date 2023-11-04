package com.codigo.msrespuestacot.service;

import com.codigo.msrespuestacot.entity.RespuestaRequerimiento;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface RespuestaRequerimientoService {

    RespuestaRequerimiento save(RespuestaRequerimiento t, HttpServletRequest header);
}
