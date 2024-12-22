package com.xavier.mozprops_api.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xavier.mozprops_api.dto.CityResponse;
import com.xavier.mozprops_api.dto.CountryDTO;
import com.xavier.mozprops_api.dto.PropertyRequest;
import com.xavier.mozprops_api.dto.PropertyResponse;
import com.xavier.mozprops_api.dto.PropertyTypeDTO;
import com.xavier.mozprops_api.dto.ProvinceResponse;
import com.xavier.mozprops_api.models.Address;
import com.xavier.mozprops_api.models.City;
import com.xavier.mozprops_api.models.Property;
import com.xavier.mozprops_api.models.PropertyDetails;
import com.xavier.mozprops_api.models.PropertyImages;
import com.xavier.mozprops_api.models.PropertyType;
import com.xavier.mozprops_api.models.Province;
import com.xavier.mozprops_api.models.User;
import com.xavier.mozprops_api.models.enums.PropertyStatus;
import com.xavier.mozprops_api.repository.PropertyRepository;
import com.xavier.mozprops_api.repository.filter.PropertyFilter;
import com.xavier.mozprops_api.service.PropertyService;
import com.xavier.mozprops_api.storage.ImageStorage;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final ImageStorage imageStorage;


    @Override
    public List<Property> getProperties(PropertyFilter propertyFilter) {
        return propertyRepository.filter(propertyFilter);
    }

    @Override
    public PropertyResponse getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        return toResponse(property);
    }

    @Override
    @Transactional
    public PropertyResponse create(@Valid PropertyRequest propertyRequest) {
       Property property = toEntity(propertyRequest);
       propertyRepository.save(property);
       return toResponse(property);
    }

    @Override
    @Transactional
    public PropertyResponse update(Long id, @Valid PropertyRequest propertyRequest) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
                property = toEntity(propertyRequest);
                propertyRepository.save(property);
                return toResponse(property);
            }
            
    @Override
    public PropertyImages uploadImage(Long propertyId, MultipartFile file) throws IOException {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String imageUrl = imageStorage.upload(file, fileName);

        PropertyImages propertyImages = PropertyImages.builder()
                .contentType(file.getContentType())
                .imageUrl(imageUrl)
                .property(property)
                .build();

        property.addImage(propertyImages);
        propertyRepository.save(property);
        return propertyImages;
    }
    
    @Override
    @Transactional
    public void deleteImage(Long propertyId, Long imageId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        PropertyImages propertyImages = property.getImages().stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Image not found"));

        property.removeImage(propertyImages);
        propertyRepository.save(property);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        propertyRepository.delete(property);
    }

    public PropertyResponse toResponse(Property property) {
        Province province = property.getAddress().getCity().getProvince();
        return PropertyResponse.builder()
            .id(property.getId())
            .title(property.getTitle())
            .description(property.getDescription())
            .propertyType(PropertyTypeDTO.builder()
                .id(property.getPropertyType().getId())
                .type(property.getPropertyType().getType())
                .build())
            .propertyStatus(property.getPropertyStatus().name())
            .price(property.getPrice())
            .street(property.getAddress().getStreet())
            .postalCode(property.getAddress().getPostalCode())
            .village(property.getAddress().getVillage())
            .city(CityResponse.builder()
                .id(property.getAddress().getCity().getId())
                .cityName(property.getAddress().getCity().getCityName())
                .province(province != null ? ProvinceResponse.builder()
                    .id(province.getId())
                    .provinceName(province.getProvinceName())
                    .country(CountryDTO.builder()
                        .id(province.getCountry().getId())
                        .countryCode(province.getCountry().getCountryCode())
                        .countryName(province.getCountry().getCountryName())
                        .build())
                    .build() : null)
                .build())
            .yearBuilt(property.getDetails().getYearBuilt())
            .numberOfRooms(property.getDetails().getNumberOfRooms())
            .numberOfBathrooms(property.getDetails().getNumberOfBathrooms())
            .numberOfFloors(property.getDetails().getNumberOfFloors())
            .area(property.getDetails().getArea())
            .pools(property.getDetails().getPools())
            .garages(property.getDetails().getGarages())
            .securitySystems(property.getDetails().getSecuritySystems())
            .elevators(property.getDetails().getElevators())
            .airConditionings(property.getDetails().getAirConditionings())
            .balconies(property.getDetails().getBalconies())
            .storages(property.getDetails().getStorages())
            .washingMachines(property.getDetails().getWashingMachines())
            .createdAt(property.getCreatedAt())
            .updatedAt(property.getUpdatedAt())
            .build();
    }

    private Property toEntity(PropertyRequest propertyRequest) {
    return Property.builder()
            .title(propertyRequest.getTitle())
            .description(propertyRequest.getDescription())
            .propertyStatus(PropertyStatus.valueOf(propertyRequest.getPropertyStatus()))
            .propertyType(PropertyType.builder()
                .id(propertyRequest.getPropertyTypeId())
                .build())
            .price(propertyRequest.getPrice().doubleValue())
            .address(Address.builder()
                .street(propertyRequest.getStreet())
                .postalCode(propertyRequest.getPostalCode())
                .village(propertyRequest.getVillage())
                .city(City.builder()
                    .id(propertyRequest.getCityId())
                    .build())
                .build())
            .details(PropertyDetails.builder()
                .yearBuilt(propertyRequest.getYearBuilt())
                .numberOfRooms(propertyRequest.getNumberOfRooms())
                .numberOfBathrooms(propertyRequest.getNumberOfBathrooms())
                .numberOfFloors(propertyRequest.getNumberOfFloors())
                .area(propertyRequest.getArea())
                .pools(propertyRequest.getPools())
                .garages(propertyRequest.getGarages())
                .securitySystems(propertyRequest.getSecuritySystems())
                .elevators(propertyRequest.getElevators())
                .airConditionings(propertyRequest.getAirConditionings())
                .balconies(propertyRequest.getBalconies())
                .storages(propertyRequest.getStorages())
                .washingMachines(propertyRequest.getWashingMachines())
                .build())
            .owner(User.builder()
                .id(propertyRequest.getOwnerId())
                .build())
            .build();
  }

  private PropertyRequest toRequest(Property property) {
    return PropertyRequest.builder()
            .title(property.getTitle())
            .description(property.getDescription())
            .propertyStatus(property.getPropertyStatus().name())
            .propertyTypeId(property.getPropertyType().getId())
            .price(BigDecimal.valueOf(property.getPrice()))
            .street(property.getAddress().getStreet())
            .postalCode(property.getAddress().getPostalCode())
            .village(property.getAddress().getVillage())
            .cityId(property.getAddress().getCity().getId())
            .yearBuilt(property.getDetails().getYearBuilt())
            .numberOfRooms(property.getDetails().getNumberOfRooms())
            .numberOfBathrooms(property.getDetails().getNumberOfBathrooms())
            .numberOfFloors(property.getDetails().getNumberOfFloors())
            .area(property.getDetails().getArea())
            .pools(property.getDetails().getPools())
            .garages(property.getDetails().getGarages())
            .securitySystems(property.getDetails().getSecuritySystems())
            .elevators(property.getDetails().getElevators())
            .airConditionings(property.getDetails().getAirConditionings())
            .balconies(property.getDetails().getBalconies())
            .storages(property.getDetails().getStorages())
            .washingMachines(property.getDetails().getWashingMachines())
            .ownerId(property.getOwner().getId())
            .build();
   }



}
