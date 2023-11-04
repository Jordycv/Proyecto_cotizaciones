package com.codigo.msrespuestacot.rest;

import com.codigo.msrespuestacot.entity.RespuestaRequerimiento;
import com.codigo.msrespuestacot.service.RespuestaRequerimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/resprequerimientos")
public class RespuestaRequerimientoController {

    @Autowired
    private RespuestaRequerimientoService respuestaRequerimientoService;




    @PostMapping("/registrar")
    public ResponseEntity<?> save(@RequestBody RespuestaRequerimiento req) {
        try {


            RespuestaRequerimiento requerimiento = respuestaRequerimientoService.save(req);

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
}
