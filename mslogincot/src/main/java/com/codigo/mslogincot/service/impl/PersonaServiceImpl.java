package com.codigo.mslogincot.service.impl;

import com.codigo.mslogincot.constantes.Constantes;
import com.codigo.mslogincot.dao.PersonaDAO;
import com.codigo.mslogincot.entity.Persona;
import com.codigo.mslogincot.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDAO personaDAO;


    @Override
    public Persona registrarPersona(Persona persona) {
        return personaDAO.save(persona);
    }

    @Override
    public List<Persona> obtenerAllPersonas() {
        return personaDAO.findAll();
    }

    @Override
    public Optional<Persona> getPersona(Integer id) {
        return personaDAO.findById(id);
    }

    @Override
    public Optional<Persona> updatePersona(Integer id, Persona persona) {
        if (personaDAO.existsById(persona.getId())) {
            Persona personaExistente=getPersona(id).get();
            personaExistente.setNombre(persona.getNombre());
            personaExistente.setApellidos(persona.getApellidos());
            personaExistente.setDireccion(persona.getDireccion());
            personaExistente.setEmail(persona.getEmail());
            personaExistente.setAreaId(persona.getAreaId());
            personaExistente.setEstado(persona.getEstado());
            personaExistente.setFechaMod(new Date());
            personaExistente.setTelefono(persona.getTelefono());

            return Optional.of(personaDAO.save(personaExistente));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Persona>  deletePersona(Integer id) {
        if (personaDAO.existsById(getPersona(id).get().getId())) {
            Persona personaExistente = getPersona(id).get();
            personaExistente.setEstado(Constantes.INACTIVO);
            personaExistente.setFechaMod(new Date());

            return Optional.of(personaDAO.save(personaExistente));
        }
        return Optional.empty();
    }
}
