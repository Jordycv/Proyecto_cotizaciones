package com.codigo.msordencompra.service.impl;

import com.codigo.msordencompra.dao.OrdenCompraDAO;
import com.codigo.msordencompra.entity.OrdenCompra;
import com.codigo.msordencompra.entity.OrdenCompraDetalle;
import com.codigo.msordencompra.security.JwtUtil;
import com.codigo.msordencompra.service.OrdenCompraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public OrdenCompra save(OrdenCompra req) {

        try {
            OrdenCompra ordenCompra = new OrdenCompra();
            ordenCompra.setIdrespuestarequerimiento(req.getIdrespuestarequerimiento());
            ordenCompra.setFechaemision(LocalDate.now());
            ordenCompra.setEstado(req.getEstado());
            ordenCompra.setObservaciones(req.getObservaciones());

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


            // pedidoCustomRepository.refresh(pedidoEntity);

            return ordenCompra;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
