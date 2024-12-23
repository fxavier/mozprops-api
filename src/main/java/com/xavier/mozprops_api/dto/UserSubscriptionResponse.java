package com.xavier.mozprops_api.dto;

import java.time.LocalDate;

import com.xavier.mozprops_api.models.SubscriptionPlan;
import com.xavier.mozprops_api.models.User;
import com.xavier.mozprops_api.models.enums.SubscriptionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscriptionResponse {
    
    private Long id;

    private User user;

    private SubscriptionPlan subscriptionPlan;

    private SubscriptionStatus status;

    private Integer propertiesPosted;

    private LocalDate startDate;

    private LocalDate endDate;

}