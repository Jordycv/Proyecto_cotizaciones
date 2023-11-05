package com.codigo.msordencompra.rest;

import com.codigo.msordencompra.entity.OrdenCompra;
import com.codigo.msordencompra.model.RespuestaRequerimiento;
import com.codigo.msordencompra.service.OrdenCompraService;
import com.codigo.msordencompra.service.impl.RespuestaRequerimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/ordencompras")
public class OrdenCompraController {
    @Autowired
    private OrdenCompraService ordenCompraService;
    @Autowired
    private RespuestaRequerimientoService respuestaRequerimientoService;

    @GetMapping("/obtenerRequerimientoXId/{id}")
    public RespuestaRequerimiento listarrequerimientoXid(@PathVariable("id") int id, HttpServletRequest authorizationHeader) {
        return respuestaRequerimientoService.listarrequerimientoXid(id, authorizationHeader);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> save(@RequestBody OrdenCompra req, HttpServletRequest header) {
        try {


            OrdenCompra ordenCompra = ordenCompraService.save(req, header);

            if (!isNull(ordenCompra)) {
                //PedidoDTO resPedido=pedidoService.findById(24L);
                //System.out.println(resPedido);
                return ResponseEntity.status(HttpStatus.CREATED).body(ordenCompra);
            }
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
