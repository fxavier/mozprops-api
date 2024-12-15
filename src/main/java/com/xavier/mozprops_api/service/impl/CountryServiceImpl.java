package com.xavier.mozprops_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xavier.mozprops_api.dto.CountryDTO;
import com.xavier.mozprops_api.models.Country;
import com.xavier.mozprops_api.repository.CountryRepository;
import com.xavier.mozprops_api.service.CountryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    
    @Override
    @Transactional
    public CountryDTO createCountry(@Valid CountryDTO countryDTO) {
        if (existsByName(countryDTO.getCountryName()) || existsByCode(countryDTO.getCountryCode())) {
            throw new RuntimeException("Country already exists");
        }
        Country country = toEntity(countryDTO);
        Country savedCountry = countryRepository.save(country);
        return toDTO(savedCountry);
    }

    @Override
    public CountryDTO getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        return toDTO(country);
    }

    @Override
    @Transactional
    public CountryDTO updateCountry(Long id, @Valid CountryDTO countryDTO) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        country.setCountryCode(countryDTO.getCountryCode());
        country.setCountryName(countryDTO.getCountryName());
        Country updatedCountry = countryRepository.save(country);
        return toDTO(updatedCountry);
    }

    @Override
    @Transactional
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        countryRepository.delete(country);
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        return countryRepository
                .findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private CountryDTO toDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .countryCode(country.getCountryCode())
                .countryName(country.getCountryName())
                .build();
    }

    private Country toEntity(CountryDTO countryDTO) {
        return Country.builder()
                .countryCode(countryDTO.getCountryCode())
                .countryName(countryDTO.getCountryName())
                .build();
    }

    private Boolean existsByName(String countryName) {
        return countryRepository.findByCountryName(countryName) != null;
    }

    private Boolean existsByCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode) != null;
    }


}
