package com.xavier.mozprops_api.dto;

import java.time.LocalDate;

import com.xavier.mozprops_api.models.enums.SubscriptionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscriptionRequest {
    
    private Long id;

    private Long userId;

    private Long subscriptionPlanId;

    private SubscriptionStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

}
