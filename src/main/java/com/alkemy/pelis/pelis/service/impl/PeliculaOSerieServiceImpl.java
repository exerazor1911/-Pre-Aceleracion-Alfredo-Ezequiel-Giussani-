package com.alkemy.pelis.pelis.service.impl;

import com.alkemy.pelis.pelis.dto.*;
import com.alkemy.pelis.pelis.entity.GeneroEntity;
import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import com.alkemy.pelis.pelis.entity.PersonajeEntity;
import com.alkemy.pelis.pelis.mapper.GeneroMapper;
import com.alkemy.pelis.pelis.mapper.PeliculaOSerieMapper;
import com.alkemy.pelis.pelis.repository.PeliculaOSerieRepository;
import com.alkemy.pelis.pelis.repository.specifications.PeliculaOSerieSpecification;
import com.alkemy.pelis.pelis.service.GeneroService;
import com.alkemy.pelis.pelis.service.PeliculaOSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeliculaOSerieServiceImpl implements PeliculaOSerieService {

    @Autowired
    private PeliculaOSerieRepository peliculaOSerieRepository;

    @Autowired
    private PeliculaOSerieMapper peliculaOSerieMapper;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private PeliculaOSerieSpecification peliculaOSerieSpecification;

    public PeliculaOSerieDTO edit(Long id, PeliculaOSerieDTO dto) {
        Optional<PeliculaOSerieEntity> encontrada = peliculaOSerieRepository.findById(id);
        PeliculaOSerieEntity modificada = peliculaOSerieMapper.editEntity(encontrada.get(), dto);
        peliculaOSerieRepository.save(modificada);
        PeliculaOSerieDTO result = peliculaOSerieMapper.peliculaOSerieEntity2DTO(modificada);

        return result;
    }

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


    public List<PeliculaOSerieDTO> getByFilters(String name, Long genre, String order) {
        PeliculaOSerieFiltersDTO filtersDTO = new PeliculaOSerieFiltersDTO(name,genre,order);
        List<PeliculaOSerieEntity> entities = peliculaOSerieRepository.findAll(peliculaOSerieSpecification.getByFilters(filtersDTO));
        List<PeliculaOSerieDTO> dtos = peliculaOSerieMapper.peliculaOSerieEntityList2DTOList(entities);

        return dtos;
    }
}
