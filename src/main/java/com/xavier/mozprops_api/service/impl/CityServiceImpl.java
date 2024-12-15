package com.xavier.mozprops_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xavier.mozprops_api.dto.CityRequest;
import com.xavier.mozprops_api.dto.CityResponse;
import com.xavier.mozprops_api.dto.CountryDTO;
import com.xavier.mozprops_api.dto.ProvinceResponse;
import com.xavier.mozprops_api.models.City;
import com.xavier.mozprops_api.models.Province;
import com.xavier.mozprops_api.repository.CityRepository;
import com.xavier.mozprops_api.service.CityService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    @Transactional
    public CityRequest createCity(CityRequest cityRequest) {
        if (isCityNameTaken(cityRequest.getCityName())) {
            throw new RuntimeException("City name is already taken");
        }
        City city = toEntity(cityRequest);
        cityRepository.save(city);
        return toRequest(city);
    }

    @Override
    public List<CityResponse> getAllCities() {
       return cityRepository
              .findAll()
              .stream()
              .map(this::toResponse)
              .toList();
    }

    @Override
    public CityResponse getCityById(Long id) {
        return toResponse(cityRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("City not found")));
    }

    @Override
    @Transactional
    public CityRequest updateCity(Long id, CityRequest cityRequest) {
        City city = cityRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("City not found"));
        city.setCityName(cityRequest.getCityName());
        city.setProvince(Province.builder().id(cityRequest.getProvinceId()).build());
        cityRepository.save(city);
        return toRequest(city);
    }

    @Override
    @Transactional
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("City not found"));
        cityRepository.delete(city);
    }

    private CityRequest toRequest(City city) {
        return CityRequest.builder()
        .cityName(city.getCityName())
        .provinceId(city.getProvince().getId())
        .build();
    }

    private CityResponse toResponse(City city) {
        return CityResponse.builder()
        .id(city.getId())
        .cityName(city.getCityName())
        .province(ProvinceResponse.builder()
            .id(city.getProvince().getId())
            .provinceName(city.getProvince().getProvinceName())
            .country(CountryDTO.builder()
                .id(city.getProvince().getCountry().getId())
                .countryName(city.getProvince().getCountry().getCountryName())
                .countryCode(city.getProvince().getCountry().getCountryCode())
                .build())
            .build())
        .build();
    }

    private City toEntity(CityRequest cityRequest) {
        return City.builder()
        .cityName(cityRequest.getCityName())
        .province(Province.builder()
           .id(cityRequest.getProvinceId())
        .build())
        .build();
    }

    private Boolean isCityNameTaken(String cityName) {
        return cityRepository.findByCityName(cityName) != null;
    }
    
}
