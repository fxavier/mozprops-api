package com.xavier.mozprops_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.xavier.mozprops_api.models.Property;
import com.xavier.mozprops_api.repository.Property.PropertyRepositoryQuery;

public interface PropertyRepository extends JpaRepository<Property, Long>, PropertyRepositoryQuery {
    
}
