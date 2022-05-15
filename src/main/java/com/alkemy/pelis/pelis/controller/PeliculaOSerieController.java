package com.alkemy.pelis.pelis.controller;

import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.service.PeliculaOSerieService;
import com.alkemy.pelis.pelis.service.impl.PeliculaOSerieServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;


@RestController
@RequestMapping("peliculasoseries")
public class PeliculaOSerieController {

    @Autowired
    private PeliculaOSerieService peliculaOSerieService;

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

}
