package com.xavier.mozprops_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xavier.mozprops_api.dto.SubscriptionPlanDTO;
import com.xavier.mozprops_api.models.SubscriptionPlan;
import com.xavier.mozprops_api.repository.SubscriptionPlanRepository;
import com.xavier.mozprops_api.service.SubscriptionPlanService;
import com.xavier.mozprops_api.service.exception.EntityNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    @Transactional
    public SubscriptionPlanDTO create(@Valid SubscriptionPlanDTO subscriptionPlanDTO) {
        SubscriptionPlan subscriptionPlan = toEntity(subscriptionPlanDTO);
        subscriptionPlanRepository.save(subscriptionPlan);
        return toDTO(subscriptionPlan);
    }

    @Override
    @Transactional
    public SubscriptionPlanDTO update(Long id, SubscriptionPlanDTO subscriptionPlanDTO) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription plan not found"));
        subscriptionPlan.setName(subscriptionPlanDTO.getName());
        subscriptionPlan.setDescription(subscriptionPlanDTO.getDescription());
        subscriptionPlan.setPrice(subscriptionPlanDTO.getPrice());
        subscriptionPlan.setPropertiesLimit(subscriptionPlanDTO.getPropertiesLimit());
        subscriptionPlan.setDuration(subscriptionPlanDTO.getDuration());
        subscriptionPlanRepository.save(subscriptionPlan);
        return toDTO(subscriptionPlan);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription plan not found"));
        subscriptionPlanRepository.delete(subscriptionPlan);
    }

    @Override
    public List<SubscriptionPlanDTO> getAll() {
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanRepository.findAll();
        return subscriptionPlans.stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public SubscriptionPlanDTO getById(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription plan not found"));
        return toDTO(subscriptionPlan);
    }

    private SubscriptionPlanDTO toDTO(SubscriptionPlan subscriptionPlan) {
        return SubscriptionPlanDTO.builder()
                .id(subscriptionPlan.getId())
                .name(subscriptionPlan.getName())
                .description(subscriptionPlan.getDescription())
                .price(subscriptionPlan.getPrice())
                .propertiesLimit(subscriptionPlan.getPropertiesLimit())
                .duration(subscriptionPlan.getDuration())
                .build();
    }

    private SubscriptionPlan toEntity(SubscriptionPlanDTO subscriptionPlanDTO) {
        return SubscriptionPlan.builder()
                .name(subscriptionPlanDTO.getName())
                .description(subscriptionPlanDTO.getDescription())
                .price(subscriptionPlanDTO.getPrice())
                .propertiesLimit(subscriptionPlanDTO.getPropertiesLimit())
                .duration(subscriptionPlanDTO.getDuration())
                .build();
    }

    
}
