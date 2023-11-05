package com.codigo.msproductos.service.impl;



import com.codigo.msproductos.constantes.Estado;
import com.codigo.msproductos.dao.RequerimientoDAO;
import com.codigo.msproductos.dto.EmisionDto;
import com.codigo.msproductos.dto.ObservacionDto;
import com.codigo.msproductos.dto.RequestDto;
import com.codigo.msproductos.dto.RequrimientoDto;
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

    @Override
    public Requerimiento emision(EmisionDto req) {

        try {
            Requerimiento requerimiento = new Requerimiento();
            requerimiento.setFechaemision(LocalDate.now());
            requerimiento.setUsuemision(req.getUsuemision());
            /*
            requerimiento.setFechaaprobacion(req.getFechaaprobacion());
            requerimiento.setUsuaprobacioon(req.getUsuaprobacioon());
            requerimiento.setFechaaprobacion(req.getFechaaprobacion());
            requerimiento.setUsuobservado(req.getUsuobservado());
            requerimiento.setFechaobservado(req.getFechaobservado());
            requerimiento.setDescripcionobservacion(req.getDescripcionobservacion());
            requerimiento.setFechaanulado(req.getFechaanulado());
            requerimiento.setUsuanulado(req.getUsuanulado()); */
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

    @Override
    public Requerimiento aprobacion(RequestDto t) {
        Requerimiento req = requerimientoDAO.findById(t.getIdreq()).get();
        req.setUsuaprobacioon(t.getUsuario());
        req.setFechaaprobacion(LocalDate.now());
        req.setEstado(Estado.APROBADO.getCodigo());
        requerimientoDAO.save(req);
        return req;
    }

    @Override
    public Requerimiento observacion(ObservacionDto t) {
        Requerimiento req = requerimientoDAO.findById(t.getIdreq()).get();
        req.setUsuobservado(t.getUsuobservado());
        req.setFechaobservado(LocalDate.now());
        req.setEstado(Estado.OBSERVADO.getCodigo());
        req.setDescripcionobservacion(t.getDescripcionobservacion());
        requerimientoDAO.save(req);
        return req;
    }

    @Override
    public Requerimiento anulacion(RequestDto t) {
        Requerimiento req = requerimientoDAO.findById(t.getIdreq()).get();
        req.setUsuanulado(t.getUsuario());
        req.setFechaanulado(LocalDate.now());
        req.setEstado(Estado.ANULADO.getCodigo());
        requerimientoDAO.save(req);
        return req;
    }

    @Override
    public List<RequrimientoDto> listarReq() {
        List<Requerimiento> lista = requerimientoDAO.findAll();
        List<RequrimientoDto> listaDto =new ArrayList<>();
       for( Requerimiento dto : lista){
           RequrimientoDto reqDto = new RequrimientoDto();
           reqDto.setIdreq(dto.getIdreq());
           reqDto.setDetalleReq(dto.getDetalleReq());
           listaDto.add(reqDto);
       }
       return listaDto;
    }

    @Override
    public RequrimientoDto obtenerxId(int id) {

        Requerimiento req =requerimientoDAO.findById(id).get();
        RequrimientoDto reqdto =new RequrimientoDto();
        reqdto.setIdreq(req.getIdreq());
        reqdto.setDetalleReq(req.getDetalleReq());
        return reqdto;
    }


}
