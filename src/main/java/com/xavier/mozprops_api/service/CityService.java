package com.xavier.mozprops_api.service;

import java.util.List;

import com.xavier.mozprops_api.dto.CityRequest;
import com.xavier.mozprops_api.dto.CityResponse;


public interface CityService {
    CityRequest createCity(CityRequest cityRequest);
    List<CityResponse> getAllCities();
    CityResponse getCityById(Long id);
    CityRequest updateCity(Long id, CityRequest cityRequest);
    void deleteCity(Long id);
}
