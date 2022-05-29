package com.alkemy.pelis.pelis.service.impl;

import com.alkemy.pelis.pelis.dto.*;
import com.alkemy.pelis.pelis.entity.GeneroEntity;
import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import com.alkemy.pelis.pelis.entity.PersonajeEntity;
import com.alkemy.pelis.pelis.exception.ParamNotFound;
import com.alkemy.pelis.pelis.mapper.GeneroMapper;
import com.alkemy.pelis.pelis.mapper.PeliculaOSerieMapper;
import com.alkemy.pelis.pelis.repository.PeliculaOSerieRepository;
import com.alkemy.pelis.pelis.repository.specifications.PeliculaOSerieSpecification;
import com.alkemy.pelis.pelis.service.GeneroService;
import com.alkemy.pelis.pelis.service.PeliculaOSerieService;
import com.alkemy.pelis.pelis.service.PersonajeService;
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

    @Autowired
    private PersonajeService personajeService;

    @Override
    public List<PeliculaOSerieDTO> getAllPeliculasOSeries() {
        List<PeliculaOSerieEntity> entidades = peliculaOSerieRepository.findAll();

        List<PeliculaOSerieDTO> peliculas = peliculaOSerieMapper.peliculaOSerieEntityList2DTOList(entidades, false);

        return peliculas;
    }

    public PeliculaOSerieDTO edit(Long id, PeliculaOSerieDTO dto) {
        Optional<PeliculaOSerieEntity> encontrada = peliculaOSerieRepository.findById(id);
        PeliculaOSerieEntity modificada = peliculaOSerieMapper.editEntity(encontrada.get(), dto);
        peliculaOSerieRepository.save(modificada);
        PeliculaOSerieDTO result = peliculaOSerieMapper.peliculaOSerieEntity2DTO(modificada, false);

        return result;
    }

    public PeliculaOSerieDTO save (PeliculaOSerieDTO dto) {
        Long generoId = dto.getGeneroId();
        GeneroEntity genero = generoService.buscarEntityPorId(generoId);
        Set<Long> personajesId = dto.getPersonajesId();
        Set<PersonajeEntity> personajes = personajeService.buscarEntitiesPorId(personajesId);
        PeliculaOSerieEntity peliculaOSerieEntity = peliculaOSerieMapper.peliculaOSerieDTO2Entity(dto, genero, personajes);
        PeliculaOSerieEntity entidadGuardada = peliculaOSerieRepository.save(peliculaOSerieEntity);
        PeliculaOSerieDTO resultado = peliculaOSerieMapper.peliculaOSerieEntity2DTO(entidadGuardada, true);


        for (PersonajeEntity p: personajes) {
            p.addPeliOSerie(peliculaOSerieEntity);
        }
        return resultado;
    }

    @Override
    public void delete(Long id) {
        peliculaOSerieRepository.deleteById(id);
    }


    public List<PeliculaOSerieDTO> getByFilters(String name, Long genre, String order) {
        PeliculaOSerieFiltersDTO filtersDTO = new PeliculaOSerieFiltersDTO(name,genre,order);
        List<PeliculaOSerieEntity> entities = peliculaOSerieRepository.findAll(peliculaOSerieSpecification.getByFilters(filtersDTO));
        List<PeliculaOSerieDTO> dtos = peliculaOSerieMapper.peliculaOSerieEntityList2DTOList(entities, false);

        return dtos;
    }

    @Override
    public PeliculaOSerieDTO buscarPorId(Long id) {
        Optional<PeliculaOSerieEntity> entity = peliculaOSerieRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Movie or Serie with the provided ID not found");
        }
        return peliculaOSerieMapper.peliculaOSerieEntity2DTO(entity.get(), false);
    }

    @Override
    public PeliculaOSerieEntity getEntityById(Long id) {
        return peliculaOSerieRepository.getById(id);
    }

    @Override
    public void addPersonaje(Long id, Long idPersonaje) {
        PeliculaOSerieEntity entity = peliculaOSerieRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = personajeService.getEntityById(idPersonaje);
        entity.addPersonaje(personajeEntity);
        peliculaOSerieRepository.save(entity);
    }

    @Override
    public void removePersonaje(Long id, Long idPersonaje) {
        PeliculaOSerieEntity entity = peliculaOSerieRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = personajeService.getEntityById(idPersonaje);
        entity.removePersonaje(personajeEntity);
        peliculaOSerieRepository.save(entity);
    }
}
