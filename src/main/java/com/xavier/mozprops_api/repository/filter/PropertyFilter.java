package com.xavier.mozprops_api.repository.filter;

import java.math.BigDecimal;

import com.xavier.mozprops_api.models.City;
import com.xavier.mozprops_api.models.Country;
import com.xavier.mozprops_api.models.Province;
import com.xavier.mozprops_api.models.PropertyType;
import com.xavier.mozprops_api.models.enums.PropertyStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyFilter {
    private String title;
    private PropertyType propertyType;
    private PropertyStatus propertyStatus;
    private City city;
    private Province province;
    private Country country;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    
}
