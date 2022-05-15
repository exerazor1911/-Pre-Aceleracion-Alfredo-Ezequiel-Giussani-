package com.alkemy.pelis.pelis.service.impl;

import com.alkemy.pelis.pelis.dto.PersonajeDTO;
import com.alkemy.pelis.pelis.dto.PersonajeFiltersDTO;
import com.alkemy.pelis.pelis.entity.PersonajeEntity;
import com.alkemy.pelis.pelis.mapper.PersonajeMapper;
import com.alkemy.pelis.pelis.repository.PersonajeRepository;
import com.alkemy.pelis.pelis.repository.specifications.PersonajeSpecification;
import com.alkemy.pelis.pelis.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeSpecification personajeSpecification;

    @Override
    public List<PersonajeDTO> getByFilters(String name, int age, Set<Long> movies, String order) {
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name,age,movies,order);
        List<PersonajeEntity> entities = personajeRepository.findAll(personajeSpecification.getByFilters(filtersDTO));
        List<PersonajeDTO> dtos = personajeMapper.personajeEntitySet2DTOList(entities);

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
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitysaved);

        return result;
    }
}
