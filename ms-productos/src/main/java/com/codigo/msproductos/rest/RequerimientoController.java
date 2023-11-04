package com.codigo.msproductos.rest;

import com.codigo.msproductos.constantes.Constantes;
import com.codigo.msproductos.entity.Requerimiento;
import com.codigo.msproductos.response.ResponseProductos;
import com.codigo.msproductos.service.ProductosService;
import com.codigo.msproductos.service.RequerimientoService;
import com.codigo.msproductos.util.ProductosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/requerimientos")
public class RequerimientoController {

    @Autowired
    private RequerimientoService requerimientoService;




    @PostMapping("/registrar")
    public ResponseEntity<?> save(@RequestBody Requerimiento req) {
        try {


            Requerimiento requerimiento = requerimientoService.save(req);

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
