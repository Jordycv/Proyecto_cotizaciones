package com.codigo.msrequerimiento.rest;

import com.codigo.msrequerimiento.dto.EmisionDto;
import com.codigo.msrequerimiento.dto.ObservacionDto;
import com.codigo.msrequerimiento.dto.RequestDto;
import com.codigo.msrequerimiento.dto.RequrimientoDto;
import com.codigo.msrequerimiento.entity.Requerimiento;
import com.codigo.msrequerimiento.service.RequerimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/requerimientos")
public class RequerimientoController {

    @Autowired
    private RequerimientoService requerimientoService;




    @PostMapping("/registrar/requerimiento")
    public ResponseEntity<?> guardarRequerimiento(@RequestBody EmisionDto req, HttpServletRequest header) {
        try {
            Requerimiento requerimiento = requerimientoService.emision(req, header);
            if (!isNull(requerimiento)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(requerimiento);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/registrar/aprobacion")
    public ResponseEntity<?> guardarAprobacion(@RequestBody RequestDto req, HttpServletRequest header) {
        try {
            Requerimiento requerimiento = requerimientoService.aprobacion(req, header);
            if (!isNull(requerimiento)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(requerimiento);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/registrar/observacion")
    public ResponseEntity<?> guardarObservacion(@RequestBody ObservacionDto req, HttpServletRequest header) {
        try {
            Requerimiento requerimiento = requerimientoService.observacion(req, header);
            if (!isNull(requerimiento)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(requerimiento);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/registrar/anulacion")
    public ResponseEntity<?> guardarAnulacion(@RequestBody RequestDto req, HttpServletRequest header) {
        try {
            Requerimiento requerimiento = requerimientoService.anulacion(req, header);
            if (!isNull(requerimiento)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(requerimiento);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/listar/requerimiento")
    public ResponseEntity<List<RequrimientoDto>> listaRequerimientos( HttpServletRequest header ){
        try {
            List<RequrimientoDto> lista =requerimientoService.listarReq( header);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
            //return productosService.obtenerAllProductos(authorizationHeader);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/obtener/{id}")
    public ResponseEntity<RequrimientoDto> listaRequerimientos(@PathVariable("id") int id, HttpServletRequest header){
        try {
            RequrimientoDto dto = requerimientoService.obtenerxId(id, header);
            if (!isNull(dto)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

}
