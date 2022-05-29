package com.alkemy.pelis.pelis.service;

import com.alkemy.pelis.pelis.dto.PersonajeDTO;
import com.alkemy.pelis.pelis.entity.PersonajeEntity;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO edit (Long id, PersonajeDTO dto);
    List<PersonajeDTO> getByFilters(String name, int age, Set<Long> movies, String order);

    List<PersonajeDTO> getAllPersonajes();

    void delete(Long id);

    PersonajeDTO save(PersonajeDTO dto);

    PersonajeDTO buscarPorId(Long id);

    void addPeliculaOSerie(Long id, Long idPeliculaOSerie);

    void removePeliculaOSerie(Long id, Long idPeliculaOSerie);

    PersonajeEntity getEntityById(Long id);

    Set<PersonajeEntity> buscarEntitiesPorId(Set<Long> personajesId);

}
