package com.codigo.msrespuestacot.util;

import com.codigo.msrespuestacot.response.ResponseRespRequerimiento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespuestaRequerimientoUtils {

    private RespuestaRequerimientoUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
        return new ResponseEntity<String>("Mensaje : " + message,httpStatus);
    }
   public static ResponseEntity<ResponseRespRequerimiento> getResponseEntityList(ResponseRespRequerimiento responseRespRequerimiento, HttpStatus httpStatus){
        return new ResponseEntity<>(responseRespRequerimiento, HttpStatus.OK);
    }

}
