package com.xavier.mozprops_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDetails {

    @Column(name = "year_built")
    private Integer yearBuilt;

    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

    @Column(name = "number_of_bathrooms")
    private Integer numberOfBathrooms;

    @Column(name = "number_of_floors")
    private Integer numberOfFloors;

    @Column(name = "area")
    private Double area;

    @Column(name = "parking_spaces")
    private Integer pools;
    @Column(name = "garages")
    private Integer garages;

    @Column(name = "security_systems")
    private Integer securitySystems;

    @Column(name = "elevators")
    private Integer elevators;

    @Column(name = "air_conditionings")
    private Integer airConditionings;

    @Column(name = "balconies")
    private Integer balconies;

    @Column(name = "storages")
    private Integer storages;

    @Column(name = "washing_machines")
    private Integer washingMachines;
}
