package com.alkemy.pelis.pelis.controller;

import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.dto.PersonajeDTO;
import com.alkemy.pelis.pelis.service.PeliculaOSerieService;
import com.alkemy.pelis.pelis.service.impl.PeliculaOSerieServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("movies")
public class PeliculaOSerieController {


    @Autowired
    private PeliculaOSerieService peliculaOSerieService;

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaOSerieDTO> getById(@PathVariable Long id) {
        PeliculaOSerieDTO dto = peliculaOSerieService.buscarPorId(id);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<PeliculaOSerieDTO>> getAll() {
        List<PeliculaOSerieDTO> peliculas = peliculaOSerieService.getAllPeliculasOSeries();
        return ResponseEntity.ok().body(peliculas);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaOSerieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {

        List<PeliculaOSerieDTO> peliculas = peliculaOSerieService.getByFilters(name,genre,order);

        return ResponseEntity.ok(peliculas);
    }

    @PostMapping
    public ResponseEntity<PeliculaOSerieDTO> save(@RequestBody PeliculaOSerieDTO dto) {
        PeliculaOSerieDTO peliculaOSerieguardada = peliculaOSerieService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaOSerieguardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        peliculaOSerieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaOSerieDTO> edit(@PathVariable Long id, @RequestBody PeliculaOSerieDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(peliculaOSerieService.edit(id,dto));
    }

}
