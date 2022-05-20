package com.alkemy.pelis.pelis.repository.specifications;

import com.alkemy.pelis.pelis.dto.PeliculaOSerieFiltersDTO;
import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

@Component
public class PeliculaOSerieSpecification {

    public Specification<PeliculaOSerieEntity> getByFilters(PeliculaOSerieFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if(filtersDTO.getGenreId() != null && filtersDTO.getGenreId() != 0) {
                predicates.add(
                        criteriaBuilder.in(root.get("genero_id")).value(filtersDTO.getGenreId());
                )
            }

            if(filtersDTO.getGenreId() != null && filtersDTO.getGenreId() != 0) {
                predicates.add(
                        criteriaBuilder.
                )
            }
    }
}
