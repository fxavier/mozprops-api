package com.xavier.mozprops_api.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.xavier.mozprops_api.dto.PropertyRequest;
import com.xavier.mozprops_api.dto.PropertyResponse;
import com.xavier.mozprops_api.models.Property;
import com.xavier.mozprops_api.repository.filter.PropertyFilter;

import jakarta.validation.Valid;

public interface PropertyService {

    List<Property> getProperties(PropertyFilter propertyFilter);

    PropertyResponse getPropertyById(Long id);

    PropertyRequest create(@Valid PropertyRequest propertyRequest, MultipartFile[] images) throws IOException;

    PropertyRequest update(Long id, @Valid PropertyRequest propertyRequest);

    void delete(Long id);

    PropertyResponse toResponse(Property property);



}
