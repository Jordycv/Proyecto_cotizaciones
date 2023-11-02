package com.codigo.mslogincot.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginUtils {
    private LoginUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
        return new ResponseEntity<String>("Mensaje : " + message,httpStatus);
    }
}
