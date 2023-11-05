package com.codigo.msrequerimiento.service;

import com.codigo.msrequerimiento.response.ResponseProductos;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ProductosService {

    ResponseEntity<String> registrarProductos(Map<String,String> requestMap, HttpServletRequest header);
    ResponseEntity<ResponseProductos> obtenerAllProductos(HttpServletRequest header);
}
