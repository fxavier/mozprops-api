package com.xavier.mozprops_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xavier.mozprops_api.models.City;




@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityName(String cityName);
    
}
