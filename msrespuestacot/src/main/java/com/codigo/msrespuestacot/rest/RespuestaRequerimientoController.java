package com.codigo.msrespuestacot.rest;

import com.codigo.msrespuestacot.model.Requerimiento;
import com.codigo.msrespuestacot.entity.RespuestaRequerimiento;
import com.codigo.msrespuestacot.service.RespuestaRequerimientoService;
import com.codigo.msrespuestacot.service.impl.ApiRequerimientoExtImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.access.prepost.PreAuthorize;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/resprequerimientos")
public class RespuestaRequerimientoController {

    @Autowired
    private RespuestaRequerimientoService respuestaRequerimientoService;

    @Autowired
    private ApiRequerimientoExtImpl apiRequerimientoExt;
    @GetMapping("/obtenerRequerimientoXId/{id}")
    public Requerimiento listarrequerimientoXid(@PathVariable("id") int id, HttpServletRequest authorizationHeader) {
        return apiRequerimientoExt.listarrequerimientoXid(id, authorizationHeader);
    }

    @GetMapping("/obtenerRequerimientos")
    public Requerimiento listarrequerimiento(HttpServletRequest authorizationHeader) {
        return apiRequerimientoExt.listarrequerimientos(authorizationHeader);
    }
    @PostMapping("/registrar")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> save(@RequestBody RespuestaRequerimiento req, HttpServletRequest header) {
        try {


            RespuestaRequerimiento requerimiento = respuestaRequerimientoService.save(req, header);

            if (!isNull(requerimiento)) {
                //PedidoDTO resPedido=pedidoService.findById(24L);
                //System.out.println(resPedido);
                return ResponseEntity.status(HttpStatus.CREATED).body(requerimiento);
            }
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<RespuestaRequerimiento> listaRequerimientosXid(@PathVariable("id") Long id, HttpServletRequest header){
        try {
            RespuestaRequerimiento respuestaRequerimiento = respuestaRequerimientoService .obtenerxId(id, header);
            if (!isNull(respuestaRequerimiento)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(respuestaRequerimiento);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
