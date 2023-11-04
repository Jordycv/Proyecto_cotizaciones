package com.codigo.msordencompra.service;

import com.codigo.msordencompra.entity.OrdenCompra;

import javax.servlet.http.HttpServletRequest;

public interface OrdenCompraService {
    OrdenCompra save(OrdenCompra t, HttpServletRequest header);
}
