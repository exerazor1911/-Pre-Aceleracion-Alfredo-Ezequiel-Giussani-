package com.alkemy.pelis.pelis.service.impl;

import com.alkemy.pelis.pelis.dto.PersonajeDTO;
import com.alkemy.pelis.pelis.dto.PersonajeFiltersDTO;
import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import com.alkemy.pelis.pelis.entity.PersonajeEntity;
import com.alkemy.pelis.pelis.exception.ParamNotFound;
import com.alkemy.pelis.pelis.mapper.PersonajeMapper;
import com.alkemy.pelis.pelis.repository.PersonajeRepository;
import com.alkemy.pelis.pelis.repository.specifications.PersonajeSpecification;
import com.alkemy.pelis.pelis.service.PeliculaOSerieService;
import com.alkemy.pelis.pelis.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeSpecification personajeSpecification;

    @Autowired
    private PeliculaOSerieService peliculaOSerieService;

    @Override
    public PersonajeDTO edit(Long id, PersonajeDTO dto) {
        Optional<PersonajeEntity> encontrado = personajeRepository.findById(id);
        if (!encontrado.isPresent()) {
            throw new ParamNotFound("ID de personaje no valido");
        }

        PersonajeEntity modificada = personajeMapper.editEntity(encontrado.get(), dto);
        personajeRepository.save(modificada);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(modificada, false);

        return result;
    }

    @Override
    public List<PersonajeDTO> getByFilters(String name, int age, Set<Long> movies, String order) {
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name,age,movies,order);
        List<PersonajeEntity> entities = personajeRepository.findAll(personajeSpecification.getByFilters(filtersDTO));
        if (entities.size() == 0) {
            throw new ParamNotFound("No se han encontrado personajes con esas especificaciones");
        }

        List<PersonajeDTO> dtos = personajeMapper.personajeEntityList2DTOList(entities);

        return dtos;
    }

    @Override
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> entidades = personajeRepository.findAll();

        List<PersonajeDTO> personajes = personajeMapper.personajeEntityList2DTOList(entidades);

        return personajes;
    }

    @Override
    public void delete(Long id) {
        personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO save(PersonajeDTO dto) {
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitysaved = personajeRepository.save(personajeEntity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitysaved, false);

        return result;
    }

    @Override
    public PersonajeDTO buscarPorId(Long id) {
        Optional<PersonajeEntity> entity = personajeRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Character with the provided ID not found");
        }
        return personajeMapper.personajeEntity2DTO(entity.get(), false);
    }

    @Override
    public void addPeliculaOSerie(Long id, Long idPeliculaOSerie) {
        PersonajeEntity entity = personajeRepository.getById(id);
        entity.getPelisOSeries().size();
        PeliculaOSerieEntity peliculaOSerieEntity = peliculaOSerieService.getEntityById(idPeliculaOSerie);
        entity.addPeliOSerie(peliculaOSerieEntity);
        personajeRepository.save(entity);
    }

    @Override
    public void removePeliculaOSerie(Long id, Long idPeliculaOSerie) {
        PersonajeEntity entity = personajeRepository.getById(id);
        entity.getPelisOSeries().size();
        PeliculaOSerieEntity peliculaOSerieEntity = peliculaOSerieService.getEntityById(idPeliculaOSerie);
        entity.removePeliOSerie(peliculaOSerieEntity);
        personajeRepository.save(entity);
    }

    public PersonajeEntity getEntityById(Long id) {
        return personajeRepository.findById(id).get();
    }
}
