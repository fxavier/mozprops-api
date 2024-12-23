package com.xavier.mozprops_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavier.mozprops_api.models.UserSubscription;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    
}
