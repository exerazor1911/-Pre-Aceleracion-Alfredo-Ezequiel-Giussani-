package com.alkemy.pelis.pelis.service.impl;

import com.alkemy.pelis.pelis.dto.GeneroDTO;
import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.entity.GeneroEntity;
import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import com.alkemy.pelis.pelis.mapper.GeneroMapper;
import com.alkemy.pelis.pelis.mapper.PeliculaOSerieMapper;
import com.alkemy.pelis.pelis.repository.PeliculaOSerieRepository;
import com.alkemy.pelis.pelis.service.GeneroService;
import com.alkemy.pelis.pelis.service.PeliculaOSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaOSerieServiceImpl implements PeliculaOSerieService {

    @Autowired
    private PeliculaOSerieRepository peliculaOSerieRepository;

    @Autowired
    private PeliculaOSerieMapper peliculaOSerieMapper;

    @Autowired
    private GeneroService generoService;

    public PeliculaOSerieDTO save (PeliculaOSerieDTO dto) {
        Long generoId = dto.getGeneroId();
        GeneroEntity genero = generoService.buscarPorId(generoId);
        PeliculaOSerieEntity peliculaOSerieEntity = peliculaOSerieMapper.peliculaOSerieDTO2Entity(dto, genero);
        PeliculaOSerieEntity entidadGuardada = peliculaOSerieRepository.save(peliculaOSerieEntity);
        PeliculaOSerieDTO resultado = peliculaOSerieMapper.peliculaOSerieEntity2DTO(entidadGuardada);

        return resultado;
    }

    @Override
    public void delete(Long id) {
        peliculaOSerieRepository.deleteById(id);
    }
}
