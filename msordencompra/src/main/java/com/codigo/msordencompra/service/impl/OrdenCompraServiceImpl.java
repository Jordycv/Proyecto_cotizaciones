package com.codigo.msordencompra.service.impl;

import com.codigo.msordencompra.dao.OrdenCompraDAO;
import com.codigo.msordencompra.entity.OrdenCompra;
import com.codigo.msordencompra.entity.OrdenCompraDetalle;
import com.codigo.msordencompra.security.JwtUtil;
import com.codigo.msordencompra.service.OrdenCompraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    @Autowired
    private OrdenCompraDAO ordenCompraDAO;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public OrdenCompra save(OrdenCompra req, HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if(jwtUtil.validateToken(token)) {
            OrdenCompra ordenCompra = new OrdenCompra();
            ordenCompra.setIdrespuestarequerimiento(req.getIdrespuestarequerimiento());
            ordenCompra.setFechaemision(LocalDate.now());
            ordenCompra.setEstado(req.getEstado());
            ordenCompra.setObservaciones(req.getObservaciones());
            ordenCompra.setTotal(req.getTotal());

            List<OrdenCompraDetalle> listordendetalle = new ArrayList<>();

            // Detalle
            req.getDetalleOrden().forEach(dp -> {

                OrdenCompraDetalle reqdetalle = dp;

                reqdetalle.setProductoid(dp.getProductoid());
                reqdetalle.setCantidad(dp.getCantidad());
                reqdetalle.setPrecio(dp.getPrecio());
                reqdetalle.setOrdenCompra(ordenCompra);
                listordendetalle.add(reqdetalle);

            });
            ordenCompra.setDetalleOrden(listordendetalle);
            ordenCompraDAO.saveAndFlush(ordenCompra);

            return ordenCompra;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String devuelveToken(HttpServletRequest header){
        String authorizationHeader = header.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
        }
        return token;
    }


}
