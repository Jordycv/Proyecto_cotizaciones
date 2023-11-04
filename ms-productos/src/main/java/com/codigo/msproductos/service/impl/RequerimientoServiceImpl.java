package com.codigo.msproductos.service.impl;



import com.codigo.msproductos.dao.RequerimientoDAO;
import com.codigo.msproductos.entity.Requerimiento;
import com.codigo.msproductos.entity.RequerimientoDetalle;
import com.codigo.msproductos.security.jwt.JwtUtil;
import com.codigo.msproductos.service.RequerimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class RequerimientoServiceImpl implements RequerimientoService {


    @Autowired
    private RequerimientoDAO requerimientoDAO;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Requerimiento save(Requerimiento req) {

        try {
            Requerimiento requerimiento = new Requerimiento();
            requerimiento.setFechaemision(LocalDate.now());
            requerimiento.setUsuemision(req.getUsuemision());
            requerimiento.setFechaaprobacion(req.getFechaaprobacion());
            requerimiento.setUsuaprobacioon(req.getUsuaprobacioon());
            requerimiento.setFechaaprobacion(req.getFechaaprobacion());
            requerimiento.setUsuobservado(req.getUsuobservado());
            requerimiento.setFechaobservado(req.getFechaobservado());
            requerimiento.setDescripcionobservacion(req.getDescripcionobservacion());
            requerimiento.setFechaanulado(req.getFechaanulado());
            requerimiento.setUsuanulado(req.getUsuanulado());
            requerimiento.setFechavencimiento(LocalDate.now().plusDays(2));
            requerimiento.setEstado(1);


            List<RequerimientoDetalle> listreqdetalle = new ArrayList<>();

            // Detalle
            req.getDetalleReq().forEach(dp -> {

                RequerimientoDetalle reqdetalle = dp;

                reqdetalle.setProductoid(dp.getProductoid());
                reqdetalle.setCantidad(dp.getCantidad());
                reqdetalle.setRequerimiento(requerimiento);
                listreqdetalle.add(reqdetalle);

            });
            requerimiento.setDetalleReq(listreqdetalle);
            requerimientoDAO.saveAndFlush(requerimiento);


            // pedidoCustomRepository.refresh(pedidoEntity);

            return requerimiento;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
