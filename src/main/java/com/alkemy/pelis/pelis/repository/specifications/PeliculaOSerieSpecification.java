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

import javax.persistence.criteria.*;

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

            if (filtersDTO.getGenre() != null && filtersDTO.getGenre() != 0) {
                Expression<String> genero = root.get("generoId");
                predicates.add(
                        genero.in(filtersDTO.getGenre())
                );
            }

            query.distinct(true);

            String orderByField = "titulo";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
