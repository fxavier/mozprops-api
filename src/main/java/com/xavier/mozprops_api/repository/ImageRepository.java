package com.xavier.mozprops_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xavier.mozprops_api.models.PropertyImages;

@Repository
public interface ImageRepository extends JpaRepository<PropertyImages, Long> {
    
}
