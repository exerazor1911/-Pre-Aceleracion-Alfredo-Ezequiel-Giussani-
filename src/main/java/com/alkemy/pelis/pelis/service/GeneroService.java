package com.alkemy.pelis.pelis.service;

import com.alkemy.pelis.pelis.dto.GeneroDTO;
import com.alkemy.pelis.pelis.entity.GeneroEntity;

import java.util.List;

public interface GeneroService {

    GeneroDTO save (GeneroDTO dto);

    List<GeneroDTO> getAllGeneros();

    GeneroDTO buscarPorId (Long generoId);

    GeneroEntity buscarEntityPorId (Long id);

    void delete(Long id);
}
