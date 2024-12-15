package com.xavier.mozprops_api.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {

    private Long id;

    private String title;

    private String description;

    private PropertyTypeDTO propertyType;

    private String propertyStatus;

    private Double price;

    private String street;

    private String postalCode;

    private String village;

    private CityResponse city;

    private Integer yearBuilt;

    private Integer numberOfRooms;

    private Integer numberOfBathrooms;

    private Integer numberOfFloors;

    private Double area;

    private Integer pools;

    private Integer garages;

    private Integer securitySystems;

    private Integer elevators;

    private Integer airConditionings;

    private Integer balconies;

    private Integer storages;

    private Integer washingMachines;

    private UserResponse owner;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    
}
