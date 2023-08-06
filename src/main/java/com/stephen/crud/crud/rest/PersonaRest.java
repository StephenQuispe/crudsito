package com.stephen.crud.crud.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephen.crud.crud.models.Persona;
import com.stephen.crud.crud.services.PersonaService;

@RestController
@RequestMapping("/api/persona/")
public class PersonaRest {
    
    @Autowired
    private PersonaService personaService;

    @PostMapping
    private ResponseEntity<Persona> guardar(@RequestBody Persona persona) {
        Persona temp = personaService.create(persona);
        try {
            return ResponseEntity.created(new URI("/api/persona"+temp.getId())).body(temp);
        } catch(Exception e) { 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("listar")
    private ResponseEntity<List<Persona>> listarTodasLasPersonas() {
        return ResponseEntity.ok(personaService.getAllPersonas());
    }
    @DeleteMapping
    private ResponseEntity<Void> eliminarPersona(@RequestBody Persona persona) {
        personaService.delete(persona);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value="{id}")
    private ResponseEntity<Optional<Persona>> listarPersonasPorID(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personaService.findById(id));
    }
}
