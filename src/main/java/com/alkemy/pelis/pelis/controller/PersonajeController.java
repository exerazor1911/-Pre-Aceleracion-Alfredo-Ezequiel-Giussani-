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

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getById(@PathVariable Long id) {
        PersonajeDTO dto = personajeService.buscarPorId(id);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<PersonajeDTO>> getAll() {
        List<PersonajeDTO> personajes =personajeService.getAllPersonajes();
        return ResponseEntity.ok().body(personajes);
    }

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

    @PostMapping("/{id}/movies/{idPeliculaOSerie}")
    public ResponseEntity<Void> addPeliculaOSerie(@PathVariable Long id, @PathVariable Long idPeliculaOSerie) {
        personajeService.addPeliculaOSerie(id, idPeliculaOSerie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/movies/{idPeliculaOSerie}")
    public ResponseEntity<Void> removePeliculaOSerie(@PathVariable Long id, @PathVariable Long idPeliculaOSerie) {
        personajeService.removePeliculaOSerie(id, idPeliculaOSerie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
