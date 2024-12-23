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

import com.xavier.mozprops_api.dto.SubscriptionPlanDTO;
import com.xavier.mozprops_api.service.SubscriptionPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscription-plans")
@RequiredArgsConstructor
public class SubscriptionPlanResource {

    private final SubscriptionPlanService subscriptionPlanService;

    @GetMapping
    public List<SubscriptionPlanDTO> getAll() {
        return subscriptionPlanService.getAll();
    }

    @PostMapping
    public ResponseEntity<SubscriptionPlanDTO> create(@RequestBody SubscriptionPlanDTO subscriptionPlanDTO) {
        SubscriptionPlanDTO createdSubscriptionPlanDTO = subscriptionPlanService.create(subscriptionPlanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscriptionPlanDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPlanDTO> getById(@PathVariable Long id) {
        SubscriptionPlanDTO subscriptionPlanDTO = subscriptionPlanService.getById(id);
        return ResponseEntity.ok(subscriptionPlanDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionPlanDTO> update(@PathVariable Long id, @RequestBody SubscriptionPlanDTO subscriptionPlanDTO) {
        SubscriptionPlanDTO updatedSubscriptionPlanDTO = subscriptionPlanService.update(id, subscriptionPlanDTO);
        return ResponseEntity.ok(updatedSubscriptionPlanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subscriptionPlanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
