package com.alkemy.pelis.pelis.repository;

import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaOSerieRepository extends JpaRepository<PeliculaOSerieEntity, Long> {
    List<PeliculaOSerieEntity> findAll (Specification<PeliculaOSerieEntity> spec);
}
