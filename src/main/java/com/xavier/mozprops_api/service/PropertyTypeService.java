package com.xavier.mozprops_api.service;

import java.util.List;

import com.xavier.mozprops_api.dto.PropertyTypeDTO;

import jakarta.validation.Valid;

public interface PropertyTypeService {
    PropertyTypeDTO createPropertyType(@Valid PropertyTypeDTO propertyTypeDTO);
    List<PropertyTypeDTO> getAllPropertyTypes();
    PropertyTypeDTO getPropertyTypeById(Long id);
    PropertyTypeDTO updatePropertyType(Long id, @Valid PropertyTypeDTO propertyTypeDTO);
    void deletePropertyType(Long id);
}
