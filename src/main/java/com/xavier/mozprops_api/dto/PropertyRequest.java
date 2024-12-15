package com.xavier.mozprops_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {

    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private BigDecimal price;
    @NotNull(message = "Property type is required")
    private Long propertyTypeId;
    @NotBlank(message = "Property status is required")
    private String propertyStatus;
    private Integer yearBuilt;
    private Integer numberOfRooms;
    private Integer numberOfBathrooms;
    private Integer numberOfFloors;
    private Double area;
    private Integer parkingSpaces;
    private Integer garages;
    private Integer securitySystems;
    private Integer elevators;
    private Integer airConditionings;
    private Integer balconies;
    private Integer pools;
    private Integer storages;
    private Integer washingMachines;
    @NotNull(message = "City is required")
    private Long cityId;
    private String street;
    private String postalCode;
    private String village;
    @NotNull(message = "Owner is required")
    private Long ownerId;


}
