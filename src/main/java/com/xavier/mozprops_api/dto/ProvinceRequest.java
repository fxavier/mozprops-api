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
public class ProvinceRequest {
    @NotBlank(message = "Province name is required")
    private String provinceName;
    @NotNull(message = "Country ID is required")
    private Long countryId;
}
