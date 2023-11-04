package com.codigo.mslogincot.rest;

import com.codigo.mslogincot.entity.Persona;
import com.codigo.mslogincot.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public List<Persona> listarPersonas(){
        return personaService.obtenerAllPersonas();
    }

    @PostMapping("/crearPersona")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona){
        Persona nuevoPersona = personaService.registrarPersona(persona);
        return new ResponseEntity<>(nuevoPersona, HttpStatus.CREATED);
    }

    @GetMapping("/{personaId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Integer personaId){
        return personaService.getPersona(personaId)
                .map(persona -> new ResponseEntity<>(persona,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{personaId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Persona> updatePersona(@PathVariable Integer personaId, @RequestBody Persona persona) {
        return personaService.updatePersona(personaId, persona)
                .map(per-> new ResponseEntity<>(per, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Persona> eliminarPersona(@PathVariable Integer id){
        Persona personarEl = personaService.deletePersona(id).get();
        return ResponseEntity.ok(personarEl);
    }


}
