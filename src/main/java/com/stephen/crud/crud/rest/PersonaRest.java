package com.stephen.crud.crud.rest;

// import java.net.URI;
// import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.stephen.crud.crud.models.Persona;
import com.stephen.crud.crud.services.PersonaService;

@Controller
public class PersonaRest {

    @Autowired
    private PersonaService personaService;

    // @PostMapping
    // private ResponseEntity<Persona> guardar(@RequestBody Persona persona) {
    //     Persona temp = personaService.create(persona);
    //     try {
    //         return ResponseEntity.created(new URI("/api/persona" + temp.getId())).body(temp);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    //     }
    // }
    @PostMapping("crear")
    private String guardar(@ModelAttribute Persona persona, Model model) {
        personaService.create(persona);
        return "redirect:/";
    }
    // @GetMapping("/listar1")
    // private ResponseEntity<List<Persona>> listarTodasLasPersonas() {
    //     return ResponseEntity.ok(personaService.getAllPersonas());
    // }

    @GetMapping({"listar", "/"})
    private String listarTodasLasPersonas(Model model) {
        model.addAttribute("titulo", "CRUD");
        model.addAttribute("personas", personaService.getAllPersonas());
        return "index";
    }
    // @DeleteMapping(value = "{id}")
    // private ResponseEntity<Void> eliminarPersona(@PathVariable("id") Long id) {
    //     Optional<Persona> personaOptional = personaService.findById(id);
    //     if (personaOptional.isPresent()) {
    //         Persona persona = personaOptional.get();
    //         personaService.delete(persona);
    //         return ResponseEntity.ok().build();
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
    @GetMapping("eliminar/{id}")
    private String eliminar(@PathVariable Long id) {
        Optional<Persona> personaOptional = personaService.findById(id);
        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();
            personaService.delete(persona);
        }
        return "redirect:/";    
    }

    // @GetMapping(value = "{id}")
    // private ResponseEntity<Optional<Persona>> listarPersonasPorID(@PathVariable("id") Long id) {
    //     return ResponseEntity.ok(personaService.findById(id));
    // }

    // @PutMapping(value = "{id}")
    // private ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
    //     persona.setId(id);
    //     Persona updatePersona = personaService.update(persona);
    //     return ResponseEntity.ok(updatePersona);
    // }

    @GetMapping("actualizar/{id}")
    private String mostrarFormularioActualizar(@PathVariable Long id, Model model) {
        model.addAttribute("persona", personaService.findById(id).get());
        return "editarForm";
    }
    @PostMapping("actualizar/{id}")
    private String actualizarPersona(@PathVariable("id") Long id, @ModelAttribute Persona persona) {
        persona.setId(id);
        personaService.update(persona);
        return "redirect:/";
    }
}
