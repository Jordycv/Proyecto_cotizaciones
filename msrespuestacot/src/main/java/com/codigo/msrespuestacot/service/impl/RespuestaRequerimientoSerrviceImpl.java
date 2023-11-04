package com.codigo.msrespuestacot.service.impl;

import com.codigo.msrespuestacot.dao.RespuestaRequerimientoDAO;
import com.codigo.msrespuestacot.entity.RespuestaRequerimiento;
import com.codigo.msrespuestacot.entity.RespuestaRequerimientoDetalle;
import com.codigo.msrespuestacot.security.JwtUtil;
import com.codigo.msrespuestacot.service.RespuestaRequerimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RespuestaRequerimientoSerrviceImpl implements RespuestaRequerimientoService {


    @Autowired
    private RespuestaRequerimientoDAO respuestaRequerimientoDAO;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public RespuestaRequerimiento save(RespuestaRequerimiento req) {

        try {
            RespuestaRequerimiento requerimiento = new RespuestaRequerimiento();
            requerimiento.setIdrequerimiento(req.getIdrequerimiento());
            requerimiento.setProveedorid(req.getProveedorid());
            requerimiento.setFecharespuesta(LocalDate.now());
            requerimiento.setTotal(req.getTotal());
            requerimiento.setObservaciones(req.getObservaciones());

            List<RespuestaRequerimientoDetalle> listreqdetalle = new ArrayList<>();

            // Detalle
            req.getDetalleReq().forEach(dp -> {

                RespuestaRequerimientoDetalle reqdetalle = dp;

                reqdetalle.setProductoid(dp.getProductoid());
                reqdetalle.setCantidad(dp.getCantidad());
                reqdetalle.setRespuestaRequerimiento(requerimiento);
                listreqdetalle.add(reqdetalle);

            });
            requerimiento.setDetalleReq(listreqdetalle);
            respuestaRequerimientoDAO.saveAndFlush(requerimiento);


            // pedidoCustomRepository.refresh(pedidoEntity);

            return requerimiento;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
