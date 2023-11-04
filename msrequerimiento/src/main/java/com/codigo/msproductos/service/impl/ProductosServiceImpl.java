package com.codigo.msproductos.service.impl;


import com.codigo.msproductos.constantes.Constantes;
import com.codigo.msproductos.dao.ProductosDAO;
import com.codigo.msproductos.entity.Productos;
import com.codigo.msproductos.response.ResponseProductos;
import com.codigo.msproductos.security.jwt.JwtUtil;
import com.codigo.msproductos.service.ProductosService;
import com.codigo.msproductos.util.ProductosUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductosServiceImpl implements ProductosService {


    @Autowired
    private ProductosDAO personaDAO;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public ResponseEntity<String> registrarProductos(Map<String, String> requestMap, HttpServletRequest header) {
        log.info("Registro Interno de una PRoducto : " + requestMap);
        try {
            String token = devuelveToken(header);
            if(jwtUtil.validateToken(token)){
                if(validateRegistroProducto(requestMap)){
                    personaDAO.save(getProductosMap(requestMap));
                    return ProductosUtils.getResponseEntity("Producto Registrado con éxito", HttpStatus.CREATED);

                }else {
                    return ProductosUtils.getResponseEntity(Constantes.DATA_INVALIDA, HttpStatus.BAD_REQUEST);
                }
            }else{
                return ProductosUtils.getResponseEntity(Constantes.DATA_INVALIDA, HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ProductosUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ResponseProductos> obtenerAllProductos(HttpServletRequest header) {
        String token = devuelveToken(header);
        try {
            if(jwtUtil.validateToken(token)){
                List<Productos> list = new ArrayList<>();
                ResponseProductos productos = new ResponseProductos();

                list =personaDAO.findAll();
                if(list.size()>0){
                    productos.setCodigo(Constantes.OPER_EXITOSA);
                    productos.setMessage(Constantes.MSG_EXITOSO);
                    productos.setProductos(list);
                    return ProductosUtils.getResponseEntityList(productos,HttpStatus.OK);
                }else {
                    productos.setCodigo(Constantes.OPER_VACIA);
                    productos.setMessage(Constantes.MSG_VACIA);
                    productos.setProductos(list);
                    return ProductosUtils.getResponseEntityList(null,HttpStatus.OK);
                }

            }else{
                return  ProductosUtils.getResponseEntityList(null, HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (JwtException e){
            ResponseProductos responseProductos = new ResponseProductos();
            responseProductos.setCodigo(Constantes.OPER_NO_EXITOSA);
            responseProductos.setMessage(Constantes.MSG_NO_EXITOSO);
            responseProductos.setProductos(null);
            return  ProductosUtils.getResponseEntityList(responseProductos, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private Boolean validateRegistroProducto(Map<String, String> requestMap){
        if(requestMap.containsKey("nombre")
                && requestMap.containsKey("precio")
                && requestMap.containsKey("cantidad")){
            return true;
        }
        return false;

    }
    private Productos getProductosMap(Map<String, String> requestMap){
        Productos productos = new Productos();
        productos.setNombre(requestMap.get("nombre"));
        productos.setPrecio(Double.parseDouble(requestMap.get("precio")));
        productos.setCantidad(Double.parseDouble(requestMap.get("cantidad")));
        productos.setEstado(Constantes.ACTIVO);

        return productos;

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
