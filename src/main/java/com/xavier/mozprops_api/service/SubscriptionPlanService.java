package com.xavier.mozprops_api.service;

import java.util.List;

import com.xavier.mozprops_api.dto.SubscriptionPlanDTO;

import jakarta.validation.Valid;

public interface SubscriptionPlanService {

    SubscriptionPlanDTO create(@Valid SubscriptionPlanDTO subscriptionPlanDTO);

    SubscriptionPlanDTO update(Long id, SubscriptionPlanDTO subscriptionPlanDTO);

    void delete(Long id);

    List<SubscriptionPlanDTO> getAll();

    SubscriptionPlanDTO getById(Long id);

}

