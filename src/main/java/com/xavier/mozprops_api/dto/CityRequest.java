package com.xavier.mozprops_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {

    @NotBlank(message = "City name is required")
    private String cityName;

    @NotNull(message = "Province ID is required")
    private Long provinceId;

}
