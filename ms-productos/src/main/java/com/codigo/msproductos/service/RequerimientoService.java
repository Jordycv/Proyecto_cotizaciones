package com.codigo.msproductos.service;

import com.codigo.msproductos.entity.Requerimiento;
import com.codigo.msproductos.response.ResponseProductos;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface RequerimientoService {

    Requerimiento save(Requerimiento t);
}
