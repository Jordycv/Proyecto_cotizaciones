package com.codigo.mslogincot.service.impl;

import com.codigo.mslogincot.dao.PersonaDAO;
import com.codigo.mslogincot.entity.Persona;
import com.codigo.mslogincot.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDAO personaDAO;

    @Override
    public ResponseEntity<String> registrarPersona(Map<String, String> requestMap) {
        log.info("Registro Interno de una Persona : " + requestMap);
        try {
            if(validateRegistroPersona(requestMap)){
                personaDAO.save(getPersonasMap(requestMap));
                return LoginUtils.getResponseEntity("Usuario Registrado con éxito", HttpStatus.CREATED);

            }else {
                return LoginUtils.getResponseEntity(Constantes.DATA_INVALIDA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return LoginUtils.getResponseEntity(Constantes.ALGO_SALIO_MAL,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<Persona> obtenerAllPersonas() {
        return null;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return null;
    }

    @Override
    public Optional<Persona> getPersona(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Persona> updatePersona(Integer id, Persona persona) {
        return Optional.empty();
    }

    @Override
    public Persona deletePersona(Integer id) {
        return null;
    }
}
