package com.codigo.mslogincot.service;

import com.codigo.mslogincot.entity.Persona;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonaService {
    Persona registrarPersona(Persona persona);
    List<Persona> obtenerAllPersonas();
    Optional<Persona> getPersona(Integer id);

    Optional<Persona> updatePersona(Integer id, Persona persona);
    Optional<Persona> deletePersona(Integer id);

}
