package com.xavier.mozprops_api.service;


import java.util.List;


import com.xavier.mozprops_api.dto.PropertyRequest;
import com.xavier.mozprops_api.dto.PropertyResponse;
import com.xavier.mozprops_api.models.Property;
import com.xavier.mozprops_api.repository.filter.PropertyFilter;

import jakarta.validation.Valid;

public interface PropertyService {

    List<Property> getProperties(PropertyFilter propertyFilter);

    PropertyResponse getPropertyById(Long id);

    PropertyRequest create(@Valid PropertyRequest propertyRequest);

    PropertyRequest update(Long id, @Valid PropertyRequest propertyRequest);

    void delete(Long id);

    PropertyResponse toResponse(Property property);



}
