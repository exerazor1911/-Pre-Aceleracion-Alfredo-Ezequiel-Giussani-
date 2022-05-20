package com.alkemy.pelis.pelis.mapper;

import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.dto.PersonajeDTO;
import com.alkemy.pelis.pelis.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaOSerieMapper peliculaOSerieMapper;

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity entity = new PersonajeEntity();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

        return entity;
    }


    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());

        List<PeliculaOSerieDTO> peliculaOSerieDTO = peliculaOSerieMapper.peliculaOSerieEntityList2DTOList(entity.getPelisOSeries());
        dto.setPeliculaOSeries(peliculaOSerieDTO);


        return dto;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> entidades) {
        List<PersonajeDTO> dtos = new ArrayList<>();

        for (PersonajeEntity p: entidades) {
            dtos.add(personajeEntity2DTO(p));
        }

        return dtos;
    }

    public PersonajeEntity editEntity(PersonajeEntity entity, PersonajeDTO dto){

    entity.setHistoria(dto.getHistoria());
    entity.setEdad(dto.getEdad());
    entity.setNombre(dto.getNombre());
    entity.setImagen(dto.getImagen());
    entity.setPeso(dto.getPeso());

    return entity;
    }
}
