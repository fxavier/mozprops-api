package com.xavier.mozprops_api.resources;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xavier.mozprops_api.dto.PropertyRequest;
import com.xavier.mozprops_api.models.City;
import com.xavier.mozprops_api.models.Country;
import com.xavier.mozprops_api.models.Property;
import com.xavier.mozprops_api.models.PropertyType;
import com.xavier.mozprops_api.models.Province;
import com.xavier.mozprops_api.models.enums.PropertyStatus;
import com.xavier.mozprops_api.repository.CityRepository;
import com.xavier.mozprops_api.repository.CountryRepository;
import com.xavier.mozprops_api.repository.PropertyTypeRepository;
import com.xavier.mozprops_api.repository.ProvinceRepository;
import com.xavier.mozprops_api.repository.filter.PropertyFilter;
import com.xavier.mozprops_api.service.PropertyService;
import com.xavier.mozprops_api.storage.ImageStorage;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyResource {

    private final PropertyService propertyService;
    private final PropertyTypeRepository propertyTypeRepository;
    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;
    private final CountryRepository countryRepository;


    /*  @GetMapping
    public ResponseEntity<List<Property>> search(PropertyFilter propertyFilter) {
        List<Property> properties = propertyService.getProperties(propertyFilter);
        return ResponseEntity.ok(properties);
    }   */

    @GetMapping
    public ResponseEntity<List<Property>> search(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String propertyTypeName,
        @RequestParam(required = false) String propertyStatus,
        @RequestParam(required = false) String cityName,
        @RequestParam(required = false) String provinceName,
        @RequestParam(required = false) String countryName,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice
    ) {
        PropertyFilter propertyFilter = new PropertyFilter();
        propertyFilter.setTitle(title);
    
        if (propertyTypeName != null) {
            PropertyType propertyType = propertyTypeRepository.findByType(propertyTypeName);
            propertyFilter.setPropertyType(propertyType);
        }
    
        if (propertyStatus != null) {
            propertyFilter.setPropertyStatus(PropertyStatus.valueOf(propertyStatus));
        }
    
        if (cityName != null) {
            City city = cityRepository.findByCityName(cityName);
            propertyFilter.setCity(city);
        }
    
        if (provinceName != null) {
            Province province = provinceRepository.findByProvinceName(provinceName);
            propertyFilter.setProvince(province);
        }
    
        if (countryName != null) {
            Country country = countryRepository.findByCountryName(countryName);
            propertyFilter.setCountry(country);
        }
    
        propertyFilter.setMinPrice(minPrice != null ? BigDecimal.valueOf(minPrice) : null);
        propertyFilter.setMaxPrice(maxPrice != null ? BigDecimal.valueOf(maxPrice) : null);
    
        List<Property> properties = propertyService.getProperties(propertyFilter);
        return ResponseEntity.ok(properties);
    }
    

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PropertyRequest> createProperty(
        @RequestPart("property") PropertyRequest propertyRequest,
        @RequestPart("images") MultipartFile[] images) throws IOException {
        PropertyRequest createdProperty = propertyService.create(propertyRequest, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    
}
