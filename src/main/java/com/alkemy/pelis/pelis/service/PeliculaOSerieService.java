package com.alkemy.pelis.pelis.service;

import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.dto.PersonajeDTO;

public interface PeliculaOSerieService {

    PeliculaOSerieDTO save(PeliculaOSerieDTO dto);

    void delete (Long id);
}
