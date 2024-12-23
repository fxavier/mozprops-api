package com.xavier.mozprops_api.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.mozprops_api.dto.UserSubscriptionRequest;
import com.xavier.mozprops_api.dto.UserSubscriptionResponse;
import com.xavier.mozprops_api.service.UserSubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user-subscriptions")
@RequiredArgsConstructor
public class UserSubscriptionResource {

    private final UserSubscriptionService userSubscriptionService;

    @GetMapping
    public List<UserSubscriptionResponse> getAll() {
        return userSubscriptionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSubscriptionResponse> getById(@PathVariable Long id) {
        UserSubscriptionResponse userSubscriptionResponse = userSubscriptionService.getById(id);
        return ResponseEntity.ok(userSubscriptionResponse);
    }

    @PostMapping
    public ResponseEntity<UserSubscriptionResponse> create(@RequestBody UserSubscriptionRequest userSubscriptionRequest) {
        UserSubscriptionResponse createdUserSubscription = userSubscriptionService.create(userSubscriptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserSubscription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSubscriptionResponse> update(@PathVariable Long id, @RequestBody UserSubscriptionRequest userSubscriptionRequest) {
        UserSubscriptionResponse updatedUserSubscription = userSubscriptionService.update(id, userSubscriptionRequest);
        return ResponseEntity.ok(updatedUserSubscription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userSubscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    
}
