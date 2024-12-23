package com.xavier.mozprops_api.service;

import java.util.List;

import com.xavier.mozprops_api.dto.UserSubscriptionRequest;
import com.xavier.mozprops_api.dto.UserSubscriptionResponse;

import jakarta.validation.Valid;

public interface UserSubscriptionService {

    UserSubscriptionResponse create(@Valid UserSubscriptionRequest userSubscriptionRequest);

    List<UserSubscriptionResponse> getAll();

    UserSubscriptionResponse getById(Long id);

    UserSubscriptionResponse update(Long id, @Valid UserSubscriptionRequest userSubscriptionRequest);

    void delete(Long id);
    
}
