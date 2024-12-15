package com.xavier.mozprops_api.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.mozprops_api.dto.PropertyTypeDTO;
import com.xavier.mozprops_api.service.PropertyTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/property-types")
@RequiredArgsConstructor
public class PropertyTypeResource {

    private final PropertyTypeService propertyTypeService;

    @PostMapping
    public ResponseEntity<PropertyTypeDTO> createPropertyType(@Valid @RequestBody PropertyTypeDTO propertyTypeDTO) {
        PropertyTypeDTO createdPropertyType = propertyTypeService.createPropertyType(propertyTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPropertyType);
    }

    @GetMapping
    public ResponseEntity<List<PropertyTypeDTO>> getAllPropertyTypes() {
        List<PropertyTypeDTO> propertyTypes = propertyTypeService.getAllPropertyTypes();
        return ResponseEntity.ok(propertyTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyTypeDTO> getPropertyTypeById(@PathVariable("id") Long id) {
        PropertyTypeDTO propertyType = propertyTypeService.getPropertyTypeById(id);
        return ResponseEntity.ok(propertyType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyTypeDTO> updatePropertyType(@PathVariable("id") Long id,
            @Valid @RequestBody PropertyTypeDTO propertyTypeDTO) {
        PropertyTypeDTO updatedPropertyType = propertyTypeService.updatePropertyType(id, propertyTypeDTO);
        return ResponseEntity.ok(updatedPropertyType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyType(@PathVariable("id") Long id) {
        propertyTypeService.deletePropertyType(id);
        return ResponseEntity.noContent().build();
    }
}
