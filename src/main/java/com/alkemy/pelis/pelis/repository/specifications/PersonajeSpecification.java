package com.alkemy.pelis.pelis.repository.specifications;

import com.alkemy.pelis.pelis.dto.PersonajeFiltersDTO;
import com.alkemy.pelis.pelis.entity.PeliculaOSerieEntity;
import com.alkemy.pelis.pelis.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;


import javax.persistence.Query;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component
public class PersonajeSpecification {


    public Specification<PersonajeEntity> getByFilters(PersonajeFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filtersDTO.getAge() != 0) {
                Expression<String> age = root.get("edad");
                predicates.add(

                        age.in(filtersDTO.getAge())
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
                Join<PeliculaOSerieEntity, PersonajeEntity> join = root.join("PeliculaOSerie", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(filtersDTO.getMovies()));
            }

            query.distinct(true);

            String orderByField = "nombre";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
