package com.xavier.mozprops_api.service.impl;

import java.util.List;

import com.xavier.mozprops_api.dto.CountryDTO;
import com.xavier.mozprops_api.dto.ProvinceRequest;
import com.xavier.mozprops_api.dto.ProvinceResponse;
import com.xavier.mozprops_api.models.Country;
import com.xavier.mozprops_api.models.Province;
import com.xavier.mozprops_api.repository.ProvinceRepository;
import com.xavier.mozprops_api.service.ProvinceService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Override
    @Transactional
    public ProvinceRequest createProvince(@Valid ProvinceRequest provinceRequest) {
        if (isProvinceNameTaken(provinceRequest.getProvinceName())) {
            throw new RuntimeException("Province name is already taken");
        }
        Province province = toEntity(provinceRequest);
        Province savedProvince = provinceRepository.save(province);
        return toRequest(savedProvince);
    }

    @Override
    public List<ProvinceResponse> getAllProvinces() {
        return provinceRepository
            .findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public ProvinceResponse getProvinceById(Long id) {
        Province province = provinceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Province not found"));
        return toResponse(province);
    }

    @Override
    @Transactional
    public ProvinceResponse updateProvince(Long id, @Valid ProvinceRequest provinceRequest) {
        Province province = provinceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Province not found"));
        province.setProvinceName(provinceRequest.getProvinceName());
        province.setCountry(Country.builder().id(provinceRequest.getCountryId()).build());
        Province updatedProvince = provinceRepository.save(province);
        return toResponse(updatedProvince);
    }

    @Override
    @Transactional
    public void deleteProvince(Long id) {
        Province province = provinceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Province not found"));
        provinceRepository.delete(province);
    }

    private ProvinceResponse toResponse(Province province) {
        return ProvinceResponse.builder()
            .id(province.getId())
            .provinceName(province.getProvinceName())
            .country(CountryDTO.builder()
                .id(province.getCountry().getId())
                .countryName(province.getCountry().getCountryName())
                .countryCode(province.getCountry().getCountryCode())
                .build())
            .build();
    }

    private ProvinceRequest toRequest(Province province) {
        return ProvinceRequest.builder()
            .provinceName(province.getProvinceName())
            .countryId(province.getCountry().getId())
            .build();
    }

    private Province toEntity(ProvinceRequest provinceRequest) {
        return Province.builder()
            .provinceName(provinceRequest.getProvinceName())
            .country(Country.builder().id(provinceRequest.getCountryId()).build())
            .build();
    }

    private Boolean isProvinceNameTaken(String provinceName) {
        return provinceRepository.findByProvinceName(provinceName) != null;
    }

   
}
