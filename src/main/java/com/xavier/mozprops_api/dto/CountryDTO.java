package com.xavier.mozprops_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDTO {
    Long id;
    String countryCode;
    @NotBlank(message = "Country name is required")
    String countryName;
    
}
