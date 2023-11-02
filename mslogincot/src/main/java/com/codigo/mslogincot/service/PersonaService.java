package com.codigo.mslogincot.service;

import com.codigo.mslogincot.entity.Persona;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonaService {
    ResponseEntity<String> registrarPersona(Map<String,String> requestMap);
    List<Persona> obtenerAllPersonas();
    Persona crearPersona(Persona persona);

    Optional<Persona> getPersona(Integer id);

    Optional<Persona> updatePersona(Integer id, Persona persona);
    Persona deletePersona(Integer id);

}
