package com.alkemy.pelis.pelis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaOSerieDTO {
    private Long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private Long generoId;
    private GeneroDTO generoDTO;
    List<PersonajeDTO> personajes;

}
