package com.xavier.mozprops_api.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String street;

    private String postalCode;

    private String village;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Transient
    private Country country;

}
