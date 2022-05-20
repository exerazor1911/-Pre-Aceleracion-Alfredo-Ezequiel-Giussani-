package com.alkemy.pelis.pelis.controller;

import com.alkemy.pelis.pelis.dto.PersonajeDTO;
import com.alkemy.pelis.pelis.service.PersonajeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int age,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order
            ) {

        List<PersonajeDTO> personajes = personajeService.getByFilters(name,age,movies,order);

       return ResponseEntity.ok(personajes);
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getAll() {
        List<PersonajeDTO> personajes =personajeService.getAllPersonajes();
        return ResponseEntity.ok().body(personajes);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO>  save(@RequestBody PersonajeDTO dto) {
        PersonajeDTO personajeGuardado = personajeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> edit(@PathVariable Long id, @RequestBody PersonajeDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(personajeService.edit(id,dto));
    }
}
