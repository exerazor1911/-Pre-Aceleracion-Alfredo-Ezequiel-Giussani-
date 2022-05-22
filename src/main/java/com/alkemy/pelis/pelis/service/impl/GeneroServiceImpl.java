package com.alkemy.pelis.pelis.service.impl;

import com.alkemy.pelis.pelis.dto.GeneroDTO;
import com.alkemy.pelis.pelis.entity.GeneroEntity;
import com.alkemy.pelis.pelis.exception.ParamNotFound;
import com.alkemy.pelis.pelis.mapper.GeneroMapper;
import com.alkemy.pelis.pelis.repository.GeneroRepository;
import com.alkemy.pelis.pelis.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroRepository generoRepository;

    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity entity = generoMapper.generoDTO2Entity(dto);
        GeneroEntity entitySaved = generoRepository.save(entity);
        GeneroDTO generoDTO = generoMapper.generoEntity2DTO(entitySaved);

        return generoDTO;
    }


    public List<GeneroDTO> getAllGeneros() {
       List<GeneroEntity> entities = generoRepository.findAll();
       List<GeneroDTO> result = generoMapper.generoEntity2DTOList(entities);

       return result;
    }

    public GeneroDTO buscarPorId(Long id) {
        Optional<GeneroEntity> entity = generoRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Genre with the provided ID not found");
        }
        GeneroDTO dto = generoMapper.generoEntity2DTO(entity.get());
        return dto;
    }

    @Override
    public GeneroEntity buscarEntityPorId(Long id) {
        GeneroEntity entity = generoRepository.findById(id).get();
        return entity;
    }

    @Override
    public void delete(Long id) {
        generoRepository.deleteById(id);
    }


}
