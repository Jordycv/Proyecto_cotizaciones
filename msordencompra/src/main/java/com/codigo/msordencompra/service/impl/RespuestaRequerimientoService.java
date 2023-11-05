package com.codigo.msordencompra.service.impl;

import com.codigo.msordencompra.model.RespuestaRequerimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class RespuestaRequerimientoService {
    @Autowired
    private RestTemplate restTemplate;

    public RespuestaRequerimiento listarrequerimientoXid(int id, HttpServletRequest httpServletRequest) {

        String token = devuelveToken(httpServletRequest);
        // Define la URL de la API a la que deseas acceder
        String apiUrl = "http://localhost:8082/api/resprequerimientos/obtener/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Configura la solicitud con el encabezado
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Realiza la solicitud HTTP GET a la API con el encabezado de autorización
        ResponseEntity<RespuestaRequerimiento> response = restTemplate.exchange(apiUrl, org.springframework.http.HttpMethod.GET, entity, RespuestaRequerimiento.class);

        // Verifica si la solicitud fue exitosa (código de estado 200)
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); // Devuelve el objeto Usuario
        } else {
            // Maneja el caso en el que la solicitud no fue exitosa
            return null; // O cualquier otro manejo de error que necesites
        }
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
