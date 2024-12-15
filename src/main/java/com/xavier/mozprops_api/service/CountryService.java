package com.xavier.mozprops_api.service;

import java.util.List;

import com.xavier.mozprops_api.dto.CountryDTO;

import jakarta.validation.Valid;

public interface CountryService {

    CountryDTO createCountry(@Valid CountryDTO countryDTO);

    List<CountryDTO> getAllCountries();

    CountryDTO getCountryById(Long id);

    CountryDTO updateCountry(Long id, @Valid CountryDTO countryDTO);

    void deleteCountry(Long id);
}
