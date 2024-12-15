package com.xavier.mozprops_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xavier.mozprops_api.dto.PropertyTypeDTO;
import com.xavier.mozprops_api.models.PropertyType;
import com.xavier.mozprops_api.repository.PropertyTypeRepository;
import com.xavier.mozprops_api.service.PropertyTypeService;
import com.xavier.mozprops_api.service.exception.PropertyTypeAlreadyExistsException;
import com.xavier.mozprops_api.service.exception.PropertyTypeNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyTypeImpl implements PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;

    @Override
    @Transactional
    public PropertyTypeDTO createPropertyType(@Valid PropertyTypeDTO propertyTypeDTO) {
        if (typePropertyExists(propertyTypeDTO.getType())) {
            throw new PropertyTypeAlreadyExistsException("Property type already exists");
        }
        PropertyType propertyType = toEntity(propertyTypeDTO);
        propertyTypeRepository.save(propertyType);
        return toDTO(propertyType);
    }

    @Override
    public List<PropertyTypeDTO> getAllPropertyTypes() {
        return propertyTypeRepository
            .findAll()
            .stream()
            .map(this::toDTO)
            .toList();
    }

    @Override
    public PropertyTypeDTO getPropertyTypeById(Long id) {
        PropertyType propertyType = propertyTypeRepository
            .findById(id)
            .orElseThrow(() -> new PropertyTypeNotFoundException("Property type not found"));
        return toDTO(propertyType);
    }

    @Override
    @Transactional
    public PropertyTypeDTO updatePropertyType(Long id, @Valid PropertyTypeDTO propertyTypeDTO) {
        PropertyType propertyType = propertyTypeRepository
            .findById(id)
            .orElseThrow(() -> new PropertyTypeNotFoundException("Property type not found"));
        propertyType.setType(propertyTypeDTO.getType());
        propertyTypeRepository.save(propertyType);
        return toDTO(propertyType);
    }

    @Override
    @Transactional
    public void deletePropertyType(Long id) {
        PropertyType propertyType = propertyTypeRepository
            .findById(id)
            .orElseThrow(() -> new PropertyTypeNotFoundException("Property type not found"));
        propertyTypeRepository.delete(propertyType);
    }

    private PropertyTypeDTO toDTO(PropertyType propertyType) {
        return PropertyTypeDTO.builder()
            .id(propertyType.getId())
            .type(propertyType.getType())
            .build();
    }

    private PropertyType toEntity(PropertyTypeDTO propertyTypeDTO) {
        return PropertyType.builder()
            .id(propertyTypeDTO.getId())
            .type(propertyTypeDTO.getType())
            .build();
    }

   private Boolean typePropertyExists(String type) {
    return propertyTypeRepository.findByType(type) != null;
   }
    
}
