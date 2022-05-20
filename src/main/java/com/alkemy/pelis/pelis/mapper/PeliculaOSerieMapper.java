package com.alkemy.pelis.pelis.mapper;


import com.alkemy.pelis.pelis.dto.GeneroDTO;
import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.entity.GeneroEntity;
import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaOSerieMapper {

    @Autowired
    private GeneroMapper generoMapper;

    public PeliculaOSerieDTO peliculaOSerieEntity2DTO(PeliculaOSerieEntity entity) {
        PeliculaOSerieDTO dto = new PeliculaOSerieDTO();
        dto.setId(entity.getId());
        dto.setCalificacion(entity.getCalificacion());
        dto.setImagen(entity.getImagen());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setTitulo(entity.getTitulo());
        dto.setGeneroId(entity.getGeneroId());
        dto.setGeneroDTO(generoMapper.generoEntity2DTO(entity.getGenero()));

        return dto;
    }

    public List<PeliculaOSerieDTO> peliculaOSerieEntityList2DTOList(List<PeliculaOSerieEntity> entities) {
        List<PeliculaOSerieDTO> listaDTO = new ArrayList<>();
        for (PeliculaOSerieEntity p: entities) {
            listaDTO.add(peliculaOSerieEntity2DTO(p));
        }
        return listaDTO;
    }

    public PeliculaOSerieEntity peliculaOSerieDTO2Entity(PeliculaOSerieDTO dto, GeneroEntity genero) {
        PeliculaOSerieEntity entity = new PeliculaOSerieEntity();
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setCalificacion(dto.getCalificacion());
        entity.setGenero(genero);
        entity.setGeneroId(dto.getGeneroId());
        return entity;
    }

    public PeliculaOSerieEntity editEntity(PeliculaOSerieEntity entity, PeliculaOSerieDTO dto) {
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());
        entity.setGenero(generoMapper.generoDTO2Entity(dto.getGeneroDTO()));

        return entity;
    }
}
