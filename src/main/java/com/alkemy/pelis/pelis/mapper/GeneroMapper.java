package com.alkemy.pelis.pelis.mapper;

import com.alkemy.pelis.pelis.dto.GeneroDTO;
import com.alkemy.pelis.pelis.dto.PeliculaOSerieDTO;
import com.alkemy.pelis.pelis.entity.GeneroEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    public GeneroEntity  generoDTO2Entity(GeneroDTO dto) {
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImagen(dto.getImagen());
        generoEntity.setNombre(dto.getNombre());

        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(entity.getId());
        generoDTO.setNombre(entity.getNombre());
        generoDTO.setImagen(entity.getImagen());

        return generoDTO;
    }

    public List<GeneroDTO> generoEntity2DTOList(List<GeneroEntity> entities) {
        List<GeneroDTO> listaDTO = new ArrayList<>();
        for (GeneroEntity g: entities) {
            listaDTO.add(generoEntity2DTO(g));
        }
        return listaDTO;
    }


}
