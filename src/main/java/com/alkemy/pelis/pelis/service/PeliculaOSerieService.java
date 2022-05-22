package com.alkemy.pelis.pelis.service;

import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.dto.PersonajeDTO;

import java.util.List;

public interface PeliculaOSerieService {

    List<PeliculaOSerieDTO> getAllPeliculasOSeries();

    PeliculaOSerieDTO edit (Long id, PeliculaOSerieDTO dto);

    PeliculaOSerieDTO save(PeliculaOSerieDTO dto);

    void delete (Long id);

    List<PeliculaOSerieDTO> getByFilters(String name, Long genre, String order);

    PeliculaOSerieDTO buscarPorId(Long id);
}
