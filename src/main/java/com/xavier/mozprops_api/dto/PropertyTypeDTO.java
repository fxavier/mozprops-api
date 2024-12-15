package com.xavier.mozprops_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeDTO {

    private Long id;

    @NotBlank(message = "Type is required")
    private String type;
    
}
