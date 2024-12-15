package com.xavier.mozprops_api.repository.Property;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.xavier.mozprops_api.models.Property;
import com.xavier.mozprops_api.repository.filter.PropertyFilter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepositoryQuery {

    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<Property> filter(PropertyFilter propertyFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Property> criteria = builder.createQuery(Property.class);
        Root<Property> root = criteria.from(Property.class);

        Predicate[] predicates = createRestrictions(propertyFilter, builder, root);
        
        if (predicates.length > 0) {
            criteria.where(builder.or(predicates));
        }
        
        TypedQuery<Property> query = manager.createQuery(criteria);
        
        return query.getResultList();
    }
        
    private Predicate[] createRestrictions(PropertyFilter propertyFilter, CriteriaBuilder builder,
        Root<Property> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(propertyFilter.getTitle())) {
            predicates.add(builder.like(
                builder.lower(root.get("title")), "%" + 
                propertyFilter.getTitle().toLowerCase() + "%" ));
        }

        if (propertyFilter.getPropertyType() != null) {
            predicates.add(builder.equal(root
                    .get("propertyType")
                    , propertyFilter.getPropertyType()));
        }

        if (propertyFilter.getPropertyStatus() != null) {
            predicates.add(builder.equal(root
                  .get("propertyStatus")
                  , propertyFilter.getPropertyStatus()));
        }

        if (propertyFilter.getCity() != null) {
            predicates.add(builder.equal(root
                  .get("address")
                  .get("city")
                  , propertyFilter.getCity()));
        }

        if (propertyFilter.getProvince() != null) {
            predicates.add(builder.equal(root
                   .get("address")
                   .get("city")
                   .get("province")
                   , propertyFilter.getProvince()));
        }

        if (propertyFilter.getCountry() != null) {
            predicates.add(builder.equal(root
                    .get("address")
                    .get("city")
                    .get("province")
                    .get("country")
                    , propertyFilter.getCountry()));
        }

        if (propertyFilter.getMinPrice() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root
                    .get("price")
                    , propertyFilter.getMinPrice()));
        }

        if (propertyFilter.getMaxPrice() != null) {
            predicates.add(builder.lessThanOrEqualTo(root
                  .get("price")
                  , propertyFilter.getMaxPrice()));
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    
}
