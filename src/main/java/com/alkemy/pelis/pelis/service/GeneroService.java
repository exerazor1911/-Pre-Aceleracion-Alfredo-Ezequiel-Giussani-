package com.alkemy.pelis.pelis.service;

import com.alkemy.pelis.pelis.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {

    GeneroDTO save (GeneroDTO dto);

    List<GeneroDTO> getAllGeneros();
}
