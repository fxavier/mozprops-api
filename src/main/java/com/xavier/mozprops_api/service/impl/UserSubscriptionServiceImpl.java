package com.xavier.mozprops_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xavier.mozprops_api.dto.UserSubscriptionRequest;
import com.xavier.mozprops_api.dto.UserSubscriptionResponse;
import com.xavier.mozprops_api.models.SubscriptionPlan;
import com.xavier.mozprops_api.models.User;
import com.xavier.mozprops_api.models.UserSubscription;
import com.xavier.mozprops_api.repository.SubscriptionPlanRepository;
import com.xavier.mozprops_api.repository.UserRepository;
import com.xavier.mozprops_api.repository.UserSubscriptionRepository;
import com.xavier.mozprops_api.service.UserSubscriptionService;
import com.xavier.mozprops_api.service.exception.EntityNotFoundException;
import com.xavier.mozprops_api.service.exception.UserNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    @Transactional
    public UserSubscriptionResponse create(@Valid UserSubscriptionRequest userSubscriptionRequest) {
        UserSubscription userSubscription = toEntity(userSubscriptionRequest);
        userSubscriptionRepository.save(userSubscription);
        return toResponse(userSubscription);
    }

    @Override
    public List<UserSubscriptionResponse> getAll() {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findAll();
        return userSubscriptions.stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public UserSubscriptionResponse getById(Long id) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User subscription not found with ID: " + id));
        return toResponse(userSubscription);
    }

    @Override
    @Transactional
    public UserSubscriptionResponse update(Long id, @Valid UserSubscriptionRequest userSubscriptionRequest) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User subscription not found with ID: " + id));
        userSubscription = toEntity(userSubscriptionRequest);
        userSubscriptionRepository.save(userSubscription);
        return toResponse(userSubscription);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User subscription not found with ID: " + id));
        userSubscriptionRepository.delete(userSubscription);
    }

    private UserSubscriptionResponse toResponse(UserSubscription userSubscription) {
        return UserSubscriptionResponse.builder()
            .id(userSubscription.getId())
            .user(userSubscription.getUser())
            .subscriptionPlan(userSubscription.getSubscriptionPlan())
            .status(userSubscription.getStatus())
            .propertiesPosted(userSubscription.getPropertiesPosted())
            .startDate(userSubscription.getStartDate().toLocalDate())
            .endDate(userSubscription.getEndDate().toLocalDate())
            .build();
    }

    private UserSubscription toEntity(UserSubscriptionRequest userSubscriptionRequest) {
        User user = userRepository.findById(userSubscriptionRequest.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userSubscriptionRequest.getUserId()));

        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(userSubscriptionRequest.getSubscriptionPlanId())
            .orElseThrow(() -> new EntityNotFoundException("Subscription plan not found with ID: " + userSubscriptionRequest.getSubscriptionPlanId()));

        return UserSubscription.builder()
            .user(user)
            .subscriptionPlan(subscriptionPlan)
            .status(userSubscriptionRequest.getStatus())
            .startDate(userSubscriptionRequest.getStartDate().atStartOfDay())
            .endDate(userSubscriptionRequest.getEndDate().atStartOfDay())
            .build();
    }
    
}
