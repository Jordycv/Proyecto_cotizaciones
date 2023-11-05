package com.codigo.msrequerimiento.util;

import com.codigo.msrequerimiento.response.ResponseProductos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductosUtils {
    private ProductosUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
        return new ResponseEntity<String>("Mensaje : " + message,httpStatus);
    }
    public static ResponseEntity<ResponseProductos> getResponseEntityList(ResponseProductos productos, HttpStatus httpStatus){
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }


}
