package com.codigo.msordencompra.rest;

import com.codigo.msordencompra.entity.OrdenCompra;
import com.codigo.msordencompra.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/ordencompras")
public class OrdenCompraController {
    @Autowired
    private OrdenCompraService ordenCompraService;




    @PostMapping("/registrar")
    public ResponseEntity<?> save(@RequestBody OrdenCompra req) {
        try {


            OrdenCompra requerimiento = ordenCompraService.save(req);

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
