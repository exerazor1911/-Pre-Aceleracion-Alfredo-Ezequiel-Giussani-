package com.alkemy.pelis.pelis.repository;

import com.alkemy.pelis.pelis.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity, Long> {
    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);
}
