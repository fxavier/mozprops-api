package com.xavier.mozprops_api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanDTO {
    
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer propertiesLimit;

    private Integer duration;

}
