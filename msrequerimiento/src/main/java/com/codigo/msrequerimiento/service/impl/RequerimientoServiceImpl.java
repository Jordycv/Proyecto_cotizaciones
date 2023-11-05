package com.codigo.msrequerimiento.service.impl;



import com.codigo.msrequerimiento.constantes.Estado;
import com.codigo.msrequerimiento.dao.RequerimientoDAO;
import com.codigo.msrequerimiento.dto.EmisionDto;
import com.codigo.msrequerimiento.dto.ObservacionDto;
import com.codigo.msrequerimiento.dto.RequestDto;
import com.codigo.msrequerimiento.dto.RequrimientoDto;
import com.codigo.msrequerimiento.entity.Requerimiento;
import com.codigo.msrequerimiento.entity.RequerimientoDetalle;
import com.codigo.msrequerimiento.security.jwt.JwtUtil;
import com.codigo.msrequerimiento.service.RequerimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public Requerimiento emision(EmisionDto req, HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if (jwtUtil.validateToken(token)) {
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

            } else {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Requerimiento aprobacion(RequestDto t, HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if (jwtUtil.validateToken(token)) {
        Requerimiento req = requerimientoDAO.findById(t.getIdreq()).get();
        req.setUsuaprobacioon(t.getUsuario());
        req.setFechaaprobacion(LocalDate.now());
        req.setEstado(Estado.APROBADO.getCodigo());
        requerimientoDAO.save(req);
        return req;
    }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Requerimiento observacion(ObservacionDto t, HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if (jwtUtil.validateToken(token)) {
        Requerimiento req = requerimientoDAO.findById(t.getIdreq()).get();
        req.setUsuobservado(t.getUsuobservado());
        req.setFechaobservado(LocalDate.now());
        req.setEstado(Estado.OBSERVADO.getCodigo());
        req.setDescripcionobservacion(t.getDescripcionobservacion());
        requerimientoDAO.save(req);
        return req;
    }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Requerimiento anulacion(RequestDto t, HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if (jwtUtil.validateToken(token)) {
        Requerimiento req = requerimientoDAO.findById(t.getIdreq()).get();
        req.setUsuanulado(t.getUsuario());
        req.setFechaanulado(LocalDate.now());
        req.setEstado(Estado.ANULADO.getCodigo());
        requerimientoDAO.save(req);
        return req;
    }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<RequrimientoDto> listarReq(HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if (jwtUtil.validateToken(token)) {
        List<Requerimiento> lista = requerimientoDAO.findAll();
        List<RequrimientoDto> listaDto = new ArrayList<>();
        for (Requerimiento dto : lista) {
            RequrimientoDto reqDto = new RequrimientoDto();
            reqDto.setIdreq(dto.getIdreq());
            reqDto.setDetalleReq(dto.getDetalleReq());
            listaDto.add(reqDto);
        }
        return listaDto;
    }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public RequrimientoDto obtenerxId(int id, HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if (jwtUtil.validateToken(token)) {
                Requerimiento req = requerimientoDAO.findById(id).get();
                RequrimientoDto reqdto = new RequrimientoDto();
                reqdto.setIdreq(req.getIdreq());
                reqdto.setDetalleReq(req.getDetalleReq());
                return reqdto;

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
