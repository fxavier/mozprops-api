package com.xavier.mozprops_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xavier.mozprops_api.models.Province;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Province findByProvinceName(String provinceName);
    
}
