package org.youcode.CITRONIX.app.specifications;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.youcode.CITRONIX.core.entities.Farm;

import java.util.ArrayList;
import java.util.List;

public class FarmSpecification {

    public static Specification<Farm> searchFarms(String name , String location , Double minSurface , Double maxSurface){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")) , "%" + name.toLowerCase() +"%"));
            }

            if (location != null && !location.isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")) , "%" + location.toLowerCase() + "%" ));
            }

            if (minSurface != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("surface") , minSurface));
            }
            if (maxSurface != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("surface") , maxSurface));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
